package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.AdminPointRequestDto;
import com.dbzz.matchingproject.dto.response.PointResponseDto;
import com.dbzz.matchingproject.entity.Point;
import com.dbzz.matchingproject.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointRepository pointRepository;

    // 포인트 조회
    @Override
    @Transactional(readOnly = true)
    public PointResponseDto checkPoint(String userId) {
        Point point = pointRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("본인 포인트만 조회가 가능합니다.")
        );
        return new PointResponseDto(point);
    }

    // 어드민 포인트 지급
    @Override
    public void givePoint(AdminPointRequestDto requestDto) {
        Point point = pointRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("회원 목록이 없습니다.")
        );

        point.changePoint(requestDto);
        pointRepository.save(point);

    }
}
