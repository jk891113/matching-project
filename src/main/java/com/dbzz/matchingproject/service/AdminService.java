package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.*;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllCustomers();

    List<SellerListResponseDto> getAllSellers();

    List<PermissionResponseDto> getPermissionRequestForms();

    PermissionResponseDto permitAuth(String userId);

    PermissionResponseDto removeAuth(String userId);
}
