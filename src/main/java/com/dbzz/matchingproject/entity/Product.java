package com.dbzz.matchingproject.entity;

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
    private long productId;

    private String sellerId;

    private String productName;

    private int price;

    private String productInfo;

    public Product(String sellerId, String productName, int price, String productInfo) {
        this.sellerId = sellerId;
        this.productName = productName;
        this.price = price;
        this.productInfo = productInfo;
    }
}
