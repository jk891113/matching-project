package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingInfoForOrderResponseDto {
    private String address;
    private String phoneNumber;

    public ShippingInfoForOrderResponseDto(ShippingInfo shippingInfo) {
        this.address = shippingInfo.getAddress();
        this.phoneNumber = shippingInfo.getPhoneNumber();
    }
}
