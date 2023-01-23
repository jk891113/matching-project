package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getAllCustomers(int page);

    List<SellerListResponseDto> getAllSellers(int page);

    List<PermissionResponseDto> getPermissionRequestForms();

    void permitAuth(String userId);

    void removeAuth(String userId);
}
