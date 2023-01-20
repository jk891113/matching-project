package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;

public interface ProfileService {
    CustomerProfileResponseDto createCustomerProfile(String userId, CustomerProfileRequestDto requestDto);

    void createSellerProfile(String userId, SellerProfileRequestDto requestDto);

    CustomerProfileResponseDto getCustomerProfileByUserId(String userId);

    ProfileResponseDto getSellerProfileByUserId(String userId);

    ProfileResponseDto updateProfile(String userId, ProfileRequestDto requestDto);
}
