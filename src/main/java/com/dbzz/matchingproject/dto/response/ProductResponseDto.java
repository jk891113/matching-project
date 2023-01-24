package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private int price;
    private int point;
    private String productInfo;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.point = product.getPoint();
        this.productInfo = product.getProductInfo();
    }
}
