package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;

public interface UserService {
    void signup(SignupRequestDto requestDto);

    AuthenticatedUserInfoDto signin(LoginRequestDto requestDto);

    void signout();
}
