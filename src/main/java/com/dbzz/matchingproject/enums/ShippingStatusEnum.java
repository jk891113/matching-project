package com.dbzz.matchingproject.enums;

import lombok.Getter;

@Getter
public enum ShippingStatusEnum {
    DEFAULT(0, "주문 수락 대기"),
    ACCEPTED(1, "배송 준비중"),
    SHIPPING(2, "배송중"),
    COMPLETED(3, "배송 완료");

    int statusCode;
    String message;

    ShippingStatusEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
