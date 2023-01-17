package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final JwtUtil jwtUtil;

    @PostMapping("/profiles/customers/{userId}")
    public CustomerProfileResponseDto createCustomerProfile(@PathVariable String userId, @RequestBody CustomerProfileRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return profileService.createCustomerProfile(userId, requestDto);
    }

    @GetMapping("/profiles/customers/{userId}")
    public CustomerProfileResponseDto getCustomerProfileByUserId(@PathVariable String userId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return profileService.getCustomerProfileByUserId(userId);
    }

    @GetMapping("/profiles/sellers/{userId}")
    public ProfileResponseDto getSellerProfileByUserId(@PathVariable String userId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return profileService.getSellerProfileByUserId(userId);
    }

    @PutMapping("/profiles/{userId}")
    public ProfileResponseDto updateProfile(@PathVariable String userId, @RequestBody ProfileRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return profileService.updateProfile(userId, requestDto);
    }
}
