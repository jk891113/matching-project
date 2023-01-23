package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;

import java.util.List;

public interface ShippingInfoService {
    ShippingInfoResponseDto createShippingInfo(ShippingInfoRequestDto requestDto, String userId);

    List<ShippingInfoResponseDto> getMyShippingInfo(String userId);

    ShippingInfoResponseDto updateShippingInfo(long shippingInfoId, ShippingInfoRequestDto requestDto);

    void deleteShippingInfo(long shippingInfoId);
}
