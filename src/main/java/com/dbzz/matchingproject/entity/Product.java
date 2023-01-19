package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.dto.request.UpdateProductRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Getter
@NoArgsConstructor
public class Product extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String userId;

    private String productName;

    private int price;

    private String productInfo;

    public Product(String userId, String productName, int price, String productInfo) {
        this.userId = userId;
        this.productName = productName;
        this.price = price;
        this.productInfo = productInfo;
    }

    public void update(UpdateProductRequestDto requestDto) {
        this.productName = requestDto.getProductName();
        this.price = requestDto.getPrice();
        this.productInfo = requestDto.getProductInfo();
    }
}