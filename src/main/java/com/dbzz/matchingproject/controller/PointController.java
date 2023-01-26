package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.AdminPointRequestDto;
import com.dbzz.matchingproject.dto.response.PointResponseDto;
import com.dbzz.matchingproject.dto.response.StatusAndDataResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.interfaces.PointService;
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
public class PointController {

    private final PointService pointService;

    // 나의 포인트 조회
    @GetMapping("/points")
    public ResponseEntity<StatusAndDataResponseDto> getAllCustomers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        PointResponseDto data = pointService.checkPoint(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "나의 포인트 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    // 어드민 포인트 지급
    @PutMapping("/admin/points")
    public ResponseEntity<StatusResponseDto> givePoint(@RequestBody AdminPointRequestDto requestDto){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "포인트 지급이 완료되었습니다.");
        pointService.givePoint(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
