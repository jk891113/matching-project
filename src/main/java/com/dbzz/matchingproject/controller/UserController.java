package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.common.RedisDao;
import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SellerAuthRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.repository.UserRepository;
import com.dbzz.matchingproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final RedisDao redisDao;

    @PostMapping("/users/signup")
    public ResponseEntity<StatusResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "회원가입 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        userService.signup(requestDto);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PostMapping("/users/signin")
    public ResponseEntity<StatusResponseDto> signin(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그인 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));

        AuthenticatedUserInfoDto userInfoDto = userService.signin(requestDto);
        String refreshToken = jwtUtil.createRefreshToken(userInfoDto.getUsername(), userInfoDto.getRole());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userInfoDto.getUsername(), userInfoDto.getRole()));
        response.addHeader(JwtUtil.REFRESH_HEADER, refreshToken);
        redisDao.setValues(refreshToken, userInfoDto.getUsername(), Duration.ofMinutes(3));

        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/seller-auth")
    public ResponseEntity<StatusResponseDto> sellerAuth(@PathVariable String userId, @RequestBody SellerAuthRequestDto requestDto){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 요청 완료");
        userService.sellerAuth(userId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 페이징
    @GetMapping("/users/seller-list")
    public List<SellerListResponseDto> getAllSellers(Pageable pageable) {
        return userService.getAllSellers(pageable);
    }

    @GetMapping("/users/signout")
    public ResponseEntity<StatusResponseDto> signout(HttpServletRequest request){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그아웃 완료");
        userService.signout(request);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
