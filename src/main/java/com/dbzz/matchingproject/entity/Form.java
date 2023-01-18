package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity(name = "forms")
@Getter
@NoArgsConstructor
public class Form extends Timestamp {
    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String item;

    public Form(String userId, String intro, String item) {
        this.userId = userId;
        this.intro = intro;
        this.item = item;
    }
}
