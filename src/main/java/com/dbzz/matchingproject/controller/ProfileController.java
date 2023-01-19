package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/profiles/customers")
    public CustomerProfileResponseDto createCustomerProfile(@RequestBody CustomerProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.createCustomerProfile(userDetails.getUserId(), requestDto);
    }

    @PostMapping("/profiles/sellers")
    public void createSellerProfile(@RequestBody SellerProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        profileService.createSellerProfile(userDetails.getUserId(), requestDto);
    }

    @GetMapping("/profiles/customers")
    public CustomerProfileResponseDto getCustomerProfileByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getCustomerProfileByUserId(userDetails.getUserId());
    }

    @GetMapping("/profiles/sellers")
    public ProfileResponseDto getSellerProfileByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getSellerProfileByUserId(userDetails.getUserId());
    }

    @PutMapping("/profiles")
    public ProfileResponseDto updateProfile(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.updateProfile(userDetails.getUserId(), requestDto);
    }
}
