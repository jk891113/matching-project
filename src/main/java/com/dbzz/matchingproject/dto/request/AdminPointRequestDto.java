package com.dbzz.matchingproject.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminPointRequestDto {
    private String userId;
    private int givePoint;

    public AdminPointRequestDto(String userId, int givePoint) {
        this.userId = userId;
        this.givePoint = givePoint;
    }
}
