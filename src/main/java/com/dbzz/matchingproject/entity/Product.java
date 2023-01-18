package com.dbzz.matchingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Getter
@NoArgsConstructor
public class Product extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String productInfo;

    public Product(String userId, String productName, int price, String productInfo) {
        this.userId =userId;
        this.productName = productName;
        this.price = price;
        this.productInfo = productInfo;
    }

//    //상품을 등록한 유저가 맞는지 확인
//    public boolean isUser(String userId) {
//        return this.userId.equals(userId);
//    }

//    public void update(String userId, UpdateProductRequestDto requestDto) { //프로덕트
//        this.productName = requestDto.getProductName();
//        this.price = requestDto.getPrice();
//        this.productInfo = requestDto.getProductInfo();
//    }
}
