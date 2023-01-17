package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private String userId;
    private String productName;
    private int price;
    private String productInfo;


    public ProductResponseDto(String userId, Product product) {
        this.userId = userId;
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productInfo = product.getProductInfo();
    }
}
