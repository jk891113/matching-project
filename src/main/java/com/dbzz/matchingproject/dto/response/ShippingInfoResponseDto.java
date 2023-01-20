package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingInfoResponseDto {
    private long shippingInfoId;
    private String userId;
    private String nameAs;
    private String address;
    private String phoneNumber;

    public ShippingInfoResponseDto(ShippingInfo shippingInfo) {
        this.shippingInfoId = shippingInfo.getShippingInfoId();
        this.userId = shippingInfo.getUserId();
        this.nameAs = shippingInfo.getNameAs();
        this.address = shippingInfo.getAddress();
        this.phoneNumber = shippingInfo.getPhoneNumber();
    }
}
