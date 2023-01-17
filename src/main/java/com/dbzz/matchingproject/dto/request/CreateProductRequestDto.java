package com.dbzz.matchingproject.dto.request;

import lombok.Getter;

@Getter
public class CreateProductRequestDto {
    private String productName;
    private int price;
    private String productInfo;
}
