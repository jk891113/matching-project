package com.dbzz.matchingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "order_items")
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    @Column(nullable = false)
    private long orderId;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int amount;

    public OrderItem(long itemId, long orderId, long productId, int quantity, int amount) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }
}
