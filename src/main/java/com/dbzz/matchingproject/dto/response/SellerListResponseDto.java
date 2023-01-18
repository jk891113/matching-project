package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerListResponseDto {
    private String userId;
    private String nickname;
    private String item;


    public SellerListResponseDto(Profile profile) {
        this.userId = profile.getUserId();
        this.nickname = profile.getNickname();
        this.item = profile.getItem();
    }
}
