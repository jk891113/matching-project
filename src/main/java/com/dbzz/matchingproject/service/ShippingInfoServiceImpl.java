package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;
import com.dbzz.matchingproject.entity.ShippingInfo;
import com.dbzz.matchingproject.repository.ShippingInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingInfoServiceImpl implements ShippingInfoService {
    private final ShippingInfoRepository shippingInfoRepository;

    @Override
    public ShippingInfoResponseDto createShippingInfo(CreateShippingInfoRequestDto requestDto, String userId) {
        ShippingInfo shippingInfo = new ShippingInfo(userId, requestDto.getNameAs(), requestDto.getAddress(), requestDto.getPhoneNumber());
        shippingInfoRepository.save(shippingInfo);
        return new ShippingInfoResponseDto(shippingInfo);
    }

    @Override
    public List<ShippingInfoResponseDto> getMyShippingInfo(String userId) {
        List<ShippingInfoResponseDto> shippingInfoResponseDtos = shippingInfoRepository.findAllByUserId(userId).stream()
                .map(shippingInfo -> new ShippingInfoResponseDto(shippingInfo))
                .collect(Collectors.toList());
        return shippingInfoResponseDtos;
    }
}
