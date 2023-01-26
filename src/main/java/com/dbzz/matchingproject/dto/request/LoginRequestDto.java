package com.dbzz.matchingproject.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {
    private String userId;
    private String password;
}
