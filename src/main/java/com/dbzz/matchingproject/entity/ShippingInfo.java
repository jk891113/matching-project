package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShippingInfo extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shippingInfoId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nameAs;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    public ShippingInfo(String userId, String nameAs, String address, String phoneNumber) {
        this.userId = userId;
        this.nameAs = nameAs;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void updateShippingInfo(ShippingInfoRequestDto requestDto) {
        this.nameAs = requestDto.getNameAs();
        this.address = requestDto.getAddress();
        this.phoneNumber = requestDto.getPhoneNumber();
    }
}
