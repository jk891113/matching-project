package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.response.PointResponseDto;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    // 나의 포인트 조회
    @GetMapping("/point")
    public PointResponseDto checkPoint(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return pointService.checkPoint(userDetails.getUserId());
    }


}
