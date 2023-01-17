package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;

public interface ProfileService {
    CustomerProfileResponseDto createCustomerProfile(String userId, CustomerProfileRequestDto requestDto);

    CustomerProfileResponseDto getCustomerProfileByUserId(String userId);

    ProfileResponseDto getSellerProfileByUserId(String userId);

    ProfileResponseDto updateProfile(String userId, ProfileRequestDto requestDto);
}
