package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
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
    public void createCustomerProfile(@PathVariable String userId, @RequestBody CustomerProfileRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        profileService.createCustomerProfile(userId, requestDto);
    }

    @GetMapping("/profiles/{userId}")
    public ProfileResponseDto getProfileByUserId(@PathVariable String userId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return profileService.getProfileByUserId(userId);
    }
}