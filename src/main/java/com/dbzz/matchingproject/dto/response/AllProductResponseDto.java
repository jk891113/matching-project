package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllProductResponseDto {
    private String userId;
    private String productName;
    private int price;
    private String productInfo;
    private int point;

    public AllProductResponseDto(Product product) {
        this.userId = product.getUserId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.productInfo = product.getProductInfo();
        this.point = product.getPoint();
    }
}
