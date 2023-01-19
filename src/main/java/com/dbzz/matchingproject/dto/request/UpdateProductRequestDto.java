package com.dbzz.matchingproject.dto.request;

import lombok.Getter;

@Getter
public class UpdateProductRequestDto {
    private String productName;
    private int price;
    private String productInfo;
}
