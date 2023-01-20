package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllCustomers(Pageable pageable);

    List<SellerListResponseDto> getAllSellers(Pageable pageable);

    List<PermissionResponseDto> getPermissionRequestForms();

    void permitAuth(String userId);

    void removeAuth(String userId);
}
