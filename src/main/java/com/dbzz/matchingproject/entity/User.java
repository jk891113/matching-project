package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.PermissionRequestDto;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User extends Timestamp{
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String userId, String password, UserRoleEnum role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public void changeSeller(User user) {
        this.role = UserRoleEnum.SELLER;
    }

    public void removeSeller(User user) {
        this.role = UserRoleEnum.CUSTOMER;
    }
}
