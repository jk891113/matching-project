package com.dbzz.matchingproject.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderRequestDto {
    private List<Long> productId;
    private List<Integer> quantity;
    private long shippingInfoId;
    private int point = 0;
}
