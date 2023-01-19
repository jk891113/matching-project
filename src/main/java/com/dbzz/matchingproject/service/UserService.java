package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;

import java.util.List;

public interface UserService {
    void signup(SignupRequestDto requestDto);

    AuthenticatedUserInfoDto signin(LoginRequestDto requestDto);

    List<ProfileResponseDto> getAllSellerList();

    void signout();
}
