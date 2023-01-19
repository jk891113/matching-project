package com.dbzz.matchingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "order_items")
@Getter
@NoArgsConstructor
public class OrderItem extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    @Column(nullable = false)
    private String customerId;

//    @Column(nullable = false)
//    private long orderId;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int amount;

    public OrderItem(String customerId, long productId, int quantity, int amount) {
        this.customerId = customerId;
//        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }
}
