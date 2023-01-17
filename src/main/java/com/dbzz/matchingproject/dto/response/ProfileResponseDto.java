package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {
    private String userId;
    private String nickname;
    private String image;
    private String intro;
    private String item;

    public ProfileResponseDto(String userId, Profile profile) {
        this.userId = userId;
        this.nickname = profile.getNickname();
        this.image = profile.getImage();
        this.intro = profile.getIntro();
        this.item = profile.getItem();
    }
}
