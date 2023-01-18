package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllCustomers();

    List<SellerListResponseDto> getAllSellers();

    List<PermissionResponseDto> getPermissionRequestForms();

    void permitAuth(String userId);

    void removeAuth(String userId);
}
