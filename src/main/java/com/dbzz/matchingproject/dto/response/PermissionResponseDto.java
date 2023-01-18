package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PermissionResponseDto {

    private String userId;
    private String intro;
    private String item;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public PermissionResponseDto(Form form) {
        this.userId = form.getUserId();
        this.intro = form.getIntro();
        this.item = form.getItem();
        this.createdAt = form.getCreatedAt();
        this.modifiedAt = form.getModifiedAt();
    }
}
