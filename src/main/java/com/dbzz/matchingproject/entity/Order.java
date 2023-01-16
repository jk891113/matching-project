package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends Timestamp{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private int totalAmount = 0;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShippingStatusEnum shippingStatus;

    public Order(long orderId, long customerId, int totalAmount, ShippingStatusEnum shippingStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.shippingStatus = shippingStatus;
    }
}
