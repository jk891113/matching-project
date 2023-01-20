package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.AdminPointRequestDto;
import com.dbzz.matchingproject.dto.response.PointResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    // 나의 포인트 조회
    @GetMapping("/point")
    public PointResponseDto checkPoint(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return pointService.checkPoint(userDetails.getUserId());
    }

    // 어드민 포인트 지급
    @PutMapping("/admin/point")
    public ResponseEntity<StatusResponseDto> givePoint(@RequestBody AdminPointRequestDto requestDto){
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "포인트 지급이 완료되었습니다.");
        pointService.givePoint(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
