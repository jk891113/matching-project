package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.AdminPointRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "points")
@Getter
@NoArgsConstructor
public class Point extends Timestamp {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private int point;


    public Point(String userId, int point) {
        this.userId = userId;
        this.point = point;
    }

    public void changePoint(AdminPointRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.point = getPoint() + requestDto.getGivePoint();

    }
}
