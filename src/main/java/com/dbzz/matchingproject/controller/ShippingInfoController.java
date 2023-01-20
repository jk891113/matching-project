package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CreateShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.security.UserDetailsServiceImpl;
import com.dbzz.matchingproject.service.ShippingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShippingInfoController {
    private final ShippingInfoService shippingInfoService;

    @PostMapping("/shippinginfo")
    public ShippingInfoResponseDto createShippingInfo(@RequestBody CreateShippingInfoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shippingInfoService.createShippingInfo(requestDto, userDetails.getUserId());
    }

    @GetMapping("/shippinginfo/my")
    public List<ShippingInfoResponseDto> getMyShippingInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shippingInfoService.getMyShippingInfo(userDetails.getUserId());
    }
}
