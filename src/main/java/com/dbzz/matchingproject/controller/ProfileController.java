package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import com.dbzz.matchingproject.dto.response.*;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/profiles/customers")
    public ResponseEntity<StatusAndDataResponseDto> createCustomerProfile(@RequestBody CustomerProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CustomerProfileResponseDto data = profileService.createCustomerProfile(userDetails.getUserId(), requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 작성 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PostMapping("/profiles/sellers")
    public ResponseEntity<StatusAndDataResponseDto> createSellerProfile(@RequestBody SellerProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PermissionResponseDto data = profileService.createSellerProfile(userDetails.getUserId(), requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "판매 권한 요청 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/profiles/customers")
    public ResponseEntity<StatusAndDataResponseDto> getCustomerProfileByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CustomerProfileResponseDto data = profileService.getCustomerProfileByUserId(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/profiles/sellers")
    public ResponseEntity<StatusAndDataResponseDto> getSellerProfileByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProfileResponseDto data = profileService.getSellerProfileByUserId(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PutMapping("/profiles")
    public ResponseEntity<StatusAndDataResponseDto> updateProfile(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProfileResponseDto data = profileService.updateProfile(userDetails.getUserId(), requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "프로필 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
