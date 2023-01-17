package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.enums.UserRoleEnum;

public interface ProfileService {
    void createCustomerProfile(String userId, CustomerProfileRequestDto requestDto);

    void getProfileByUserId();

    void getSellerProfileByUserId();

    void updateProfile();
}
