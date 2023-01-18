package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "profiles")
@Getter
@NoArgsConstructor
public class Profile extends Timestamp {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nickname;

    private String image = "기본 프로필 사진";

    private String intro;

    private String item;

    public Profile(String userId, String nickname, String image) {
        this.userId = userId;
        this.nickname = nickname;
        this.image = image;
    }

    public Profile(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public Profile(String userId, String nickname, String intro, String item) {
        this.userId = userId;
        this.nickname = nickname;
        this.intro = intro;
        this.item = item;
    }

    public Profile(String userId, String nickname, String image, String intro, String item) {
        this.userId = userId;
        this.nickname = nickname;
        this.image = image;
        this.intro = intro;
        this.item = item;
    }

    public void updateWithImage(ProfileRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.image = requestDto.getImage();
        this.intro = requestDto.getIntro();
        this.item = requestDto.getItem();
    }

    public void updateWithoutImage(ProfileRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.intro = requestDto.getIntro();
        this.item = requestDto.getItem();
    }
}
