package com.dbzz.matchingproject.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderRequestDto {
    private List<Long> productIdList;
    private List<Integer> quantityList;
    private long shippingInfoId;
    private int point = 0;
}
