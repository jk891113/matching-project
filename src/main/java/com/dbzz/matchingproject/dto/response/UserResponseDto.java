package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String userId;
    private LocalDateTime createdAt;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();
    }

}
