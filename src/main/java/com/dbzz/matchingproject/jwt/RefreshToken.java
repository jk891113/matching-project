package com.dbzz.matchingproject.jwt;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refresh_token_id")
    private Long id;

    private String userId;
    private String token;

    private RefreshToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public static RefreshToken createToken(String userId, String token) {
        return new RefreshToken(userId, token);
    }

    public void changeToken(String token) {
        this.token = token;
    }
}
