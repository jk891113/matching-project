package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.ShippingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShippingInfoController {
    private final ShippingInfoService shippingInfoService;

    @PostMapping("/shippinginfo")
    public ShippingInfoResponseDto createShippingInfo(@RequestBody ShippingInfoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shippingInfoService.createShippingInfo(requestDto, userDetails.getUserId());
    }

    @GetMapping("/shippinginfo/my")
    public List<ShippingInfoResponseDto> getMyShippingInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return shippingInfoService.getMyShippingInfo(userDetails.getUserId());
    }

    @PutMapping("/shippinginfo/{shippingInfoId}")
    public ShippingInfoResponseDto updateShippingInfo(@PathVariable long shippingInfoId, @RequestBody ShippingInfoRequestDto requestDto) {
        return shippingInfoService.updateShippingInfo(shippingInfoId, requestDto);
    }

    @DeleteMapping("/shippinginfo/{shippingInfoId}")
    public ResponseEntity<StatusResponseDto> deleteShippingInfo(@PathVariable long shippingInfoId) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "로그인 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        shippingInfoService.deleteShippingInfo(shippingInfoId);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
