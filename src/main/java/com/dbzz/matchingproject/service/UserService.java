package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SellerAuthRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void signup(SignupRequestDto requestDto);

    AuthenticatedUserInfoDto signin(LoginRequestDto requestDto);

//    List<ProfileResponseDto> getAllSellerList();

    void signout(HttpServletRequest request);

    void sellerAuth(String userId, SellerAuthRequestDto requestDto, String userDetails);

    List<SellerListResponseDto> getAllSellers(int page);
}
