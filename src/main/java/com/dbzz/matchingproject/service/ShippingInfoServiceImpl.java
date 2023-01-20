package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.entity.ShippingInfo;
import com.dbzz.matchingproject.repository.ShippingInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingInfoServiceImpl implements ShippingInfoService {
    private final ShippingInfoRepository shippingInfoRepository;

    @Override
    public ShippingInfoResponseDto createShippingInfo(ShippingInfoRequestDto requestDto, String userId) {
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

    @Override
    public ShippingInfoResponseDto updateShippingInfo(long shippingInfoId, ShippingInfoRequestDto requestDto) {
        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(shippingInfoId).orElseThrow(
                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
        );
        shippingInfo.updateShippingInfo(requestDto);
        shippingInfoRepository.save(shippingInfo);
        return new ShippingInfoResponseDto(shippingInfo);
    }

    @Override
    public void deleteShippingInfo(long shippingInfoId) {
        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(shippingInfoId).orElseThrow(
                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
        );
        shippingInfoRepository.delete(shippingInfo);
    }
}
