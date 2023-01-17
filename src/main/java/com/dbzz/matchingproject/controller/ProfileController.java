package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
