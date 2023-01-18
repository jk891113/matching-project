package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.PermissionRequestDto;
import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.UserResponseDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @GetMapping("/admin/customerList")
    public List<UserResponseDto> getAllCustomers(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.getAllCustomers();
    }

    @GetMapping("/admin/sellerList")
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
    public PermissionResponseDto permitAuth(@PathVariable String userId, @RequestBody PermissionRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.permitAuth(userId, requestDto);
    }

    @PutMapping("/admin/sellers/{userId}/permission")
    public PermissionResponseDto removeAuth(@PathVariable String userId, @RequestBody PermissionRequestDto requestDto, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return adminService.removeAuth(userId, requestDto);
    }
}
