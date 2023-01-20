package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.enums.ShippingStatusEnum;
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

//    @Column(nullable = false)
//    private long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String sellerId;

    @Column(nullable = false)
    private long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShippingStatusEnum shippingStatus = ShippingStatusEnum.DEFAULT;

    public OrderItem(String sellerId, long productId, int quantity, int amount, Order order) {
        this.sellerId = sellerId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.order = order;
    }

    public void acceptOrder(ShippingStatusEnum shippingStatus) {
        if (shippingStatus.equals(ShippingStatusEnum.DEFAULT)) {
            this.shippingStatus = ShippingStatusEnum.ACCEPTED;
        } else if (shippingStatus.equals(ShippingStatusEnum.ACCEPTED)) {
            this.shippingStatus = ShippingStatusEnum.SHIPPING;
        } else if (shippingStatus.equals(ShippingStatusEnum.SHIPPING)) {
            this.shippingStatus = ShippingStatusEnum.COMPLETED;
        }
    }
}
