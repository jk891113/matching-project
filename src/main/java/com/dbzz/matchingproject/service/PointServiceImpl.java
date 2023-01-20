package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.PointResponseDto;
import com.dbzz.matchingproject.entity.Point;
import com.dbzz.matchingproject.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointRepository pointRepository;

    @Override
    public PointResponseDto checkPoint(String userId) {
        Point point = pointRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("본인 포인트만 조회가 가능합니다.")
        );
        return new PointResponseDto(point);
    }
}
