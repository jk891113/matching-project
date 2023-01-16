package com.dbzz.matchingproject.entity;

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
    private String content;

    public Form(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
