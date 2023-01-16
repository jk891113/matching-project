package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.enums.StatusEnum;
import lombok.Getter;

@Getter
public class StatusResponseDto {
    private StatusEnum status;
    private String message;

    public StatusResponseDto(StatusEnum status, String message) {
        this.status = status;
        this.message = message;
    }
}
