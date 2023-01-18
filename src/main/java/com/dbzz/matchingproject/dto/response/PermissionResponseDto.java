package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PermissionResponseDto {

    private String userId;
    private String nickname;
    private String item;
    private UserRoleEnum role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PermissionResponseDto(String userId, Profile profile) {
        this.userId = userId;
        this.nickname = profile.getNickname();
        this.item = profile.getItem();
        this.createdAt = profile.getCreatedAt();
        this.modifiedAt = profile.getModifiedAt();
    }

    public PermissionResponseDto(String userId, User user) {
        this.userId = userId;
        this.role = user.getRole();
    }
}
