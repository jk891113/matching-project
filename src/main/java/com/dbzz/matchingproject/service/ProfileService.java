package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.enums.UserRoleEnum;

public interface ProfileService {
    void createCustomerProfile();

    void getProfileByUserId();

    void getSellerProfileByUserId();

    void updateProfile();
}
