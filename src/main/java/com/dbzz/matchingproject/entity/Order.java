package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(nullable = false)
    private String customerId = "";

    @Column(nullable = false)
    private String sellerId = "";

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Column(nullable = false)
    private int totalAmount = 0;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ShippingStatusEnum shippingStatus = ShippingStatusEnum.DEFAULT;

    public Order(String customerId, String sellerId, int totalAmount) {
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.totalAmount = totalAmount;
    }

    public void putDatasInOrder(String customerId, String sellerId, int totalAmount) {
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.totalAmount = totalAmount;
    }

    public void updateShippingStatus(Order order) {
        for (int i = 0; i < order.getOrderItemList().size(); i++) {
            if (order.getOrderItemList().get(i).getShippingStatus().equals(ShippingStatusEnum.ACCEPTED)) {
                this.shippingStatus = ShippingStatusEnum.ACCEPTED;
//            } else if (this.getOrderItemList().get(i))
            }
        }
    }
}
