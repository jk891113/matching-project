package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

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
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userInfoDto.getUsername(), userInfoDto.getRole()));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
