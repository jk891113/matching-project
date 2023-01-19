package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.dto.response.UserResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    // 페이징
    @GetMapping("/admin/customer-list")
    public List<UserResponseDto> getAllCustomers(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.getAllCustomers();
    }

    // 페이징
    @GetMapping("/admin/seller-list")
    public List<SellerListResponseDto> getAllSellers(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.getAllSellers();
    }

    @GetMapping("/admin/permission")
    public List<PermissionResponseDto> getPermissionRequestForms(HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.getPermissionRequestForms();
    }

    @PutMapping("/admin/users/{userId}/permission")
    public ResponseEntity<StatusResponseDto> permitAuth(@PathVariable String userId, HttpServletRequest request){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 승인 완료");
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        adminService.permitAuth(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/admin/sellers/{userId}/permission")
    public ResponseEntity<StatusResponseDto> removeAuth(@PathVariable String userId, HttpServletRequest request){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "판매자 권한 회수 완료");
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        adminService.removeAuth(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
