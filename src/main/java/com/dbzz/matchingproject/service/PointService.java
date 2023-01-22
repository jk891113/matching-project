package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.AdminPointRequestDto;
import com.dbzz.matchingproject.dto.response.PointResponseDto;

import java.util.List;

public interface PointService {

    PointResponseDto checkPoint(String userId);

    void givePoint(AdminPointRequestDto requestDto);





}
