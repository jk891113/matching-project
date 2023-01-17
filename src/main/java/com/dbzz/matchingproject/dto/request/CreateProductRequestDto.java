package com.dbzz.matchingproject.dto.request;

import com.dbzz.matchingproject.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CreateProductRequestDto {
    private String productName;
    private int price;
    private String productInfo;
}
