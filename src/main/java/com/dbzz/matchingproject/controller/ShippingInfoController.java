package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.ShippingInfoResponseDto;
import com.dbzz.matchingproject.dto.response.StatusAndDataResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.interfaces.ShippingInfoService;
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
    public ResponseEntity<StatusAndDataResponseDto> createShippingInfo(@RequestBody ShippingInfoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ShippingInfoResponseDto data = shippingInfoService.createShippingInfo(requestDto, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "배송정보 작성 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/shippinginfo/my")
    public ResponseEntity<StatusAndDataResponseDto> getMyShippingInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ShippingInfoResponseDto> data = shippingInfoService.getMyShippingInfo(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "배송정보 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PutMapping("/shippinginfo/{shippingInfoId}")
    public ResponseEntity<StatusAndDataResponseDto> updateShippingInfo(@PathVariable long shippingInfoId, @RequestBody ShippingInfoRequestDto requestDto) {
        ShippingInfoResponseDto data = shippingInfoService.updateShippingInfo(shippingInfoId, requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "배송정보 수정 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @DeleteMapping("/shippinginfo/{shippingInfoId}")
    public ResponseEntity<StatusResponseDto> deleteShippingInfo(@PathVariable long shippingInfoId) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "배송정보 삭제 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        shippingInfoService.deleteShippingInfo(shippingInfoId);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
