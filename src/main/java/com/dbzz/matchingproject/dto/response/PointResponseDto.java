package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PointResponseDto {
    private String userId;
    private int point;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PointResponseDto(Point point) {
        this.userId = point.getUserId();
        this.point = point.getPoint();
        this.createdAt = point.getCreatedAt();
        this.modifiedAt = point.getModifiedAt();
    }

}
