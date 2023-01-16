package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users/signup")
    public void signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    @PostMapping("/users/login")
    public void login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        AuthenticatedUserInfoDto userInfoDto = userService.login(requestDto);
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userInfoDto.getUsername(), userInfoDto.getRole()));
    }
}
