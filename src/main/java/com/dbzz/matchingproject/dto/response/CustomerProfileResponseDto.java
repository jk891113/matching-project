package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerProfileResponseDto {
    private String userId;
    private String nickname;
    private String image;

    public CustomerProfileResponseDto(String userId, Profile profile) {
        this.userId = userId;
        this.nickname = profile.getNickname();
        this.image = profile.getImage();
    }
}
