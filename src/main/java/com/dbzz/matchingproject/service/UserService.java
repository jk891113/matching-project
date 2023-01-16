package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;

public interface UserService {
    public void signup(SignupRequestDto requestDto);

    public AuthenticatedUserInfoDto login(LoginRequestDto requestDto);

    public void logout();
}
