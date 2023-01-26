package com.dbzz.matchingproject.service.interfaces;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;

public interface ProfileService {
    CustomerProfileResponseDto createCustomerProfile(String userId, CustomerProfileRequestDto requestDto);

    PermissionResponseDto createSellerProfile(String userId, SellerProfileRequestDto requestDto);

    CustomerProfileResponseDto getCustomerProfileByUserId(String userId);

    ProfileResponseDto getSellerProfileByUserId(String userId);

    ProfileResponseDto updateProfile(String userId, ProfileRequestDto requestDto);
}
