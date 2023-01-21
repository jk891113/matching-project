package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {
    private long itemId;
    private long productId;
    private int quantity;
    private int amount;
    private String shippingStatus;

    public OrderItemResponseDto(OrderItem orderItem) {
        this.itemId = orderItem.getItemId();
        this.productId = orderItem.getProductId();
        this.quantity = orderItem.getQuantity();
        this.amount = orderItem.getAmount();
        this.shippingStatus = orderItem.getShippingStatus().getMessage();
    }
}
