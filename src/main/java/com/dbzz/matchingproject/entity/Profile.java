package com.dbzz.matchingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "profiles")
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nickname;

    private String image;

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
}
