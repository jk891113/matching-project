package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.enums.StatusEnum;
import lombok.Getter;

@Getter
public class StatusAndDataResponseDto {
    private StatusEnum status;
    private String message;
    private Object data;

    public StatusAndDataResponseDto(StatusEnum status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
