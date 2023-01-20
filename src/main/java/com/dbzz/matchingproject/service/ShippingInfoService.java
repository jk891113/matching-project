package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;

import java.util.List;

public interface ShippingInfoService {
    ShippingInfoResponseDto createShippingInfo(CreateShippingInfoRequestDto requestDto, String userId);

    List<ShippingInfoResponseDto> getMyShippingInfo(String userId);
}
