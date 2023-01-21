package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Order;
import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderForCustomerResponseDto {
    private long orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int totalAmount;
    private String shippingStatus;
    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    public OrderForCustomerResponseDto(Order order) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        this.totalAmount = order.getTotalAmount();
        this.shippingStatus = order.getShippingStatus().getMessage();
        List<OrderItemResponseDto> orderItemResponseDtos = order.getOrderItemList().stream()
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
        this.orderItemList = orderItemResponseDtos;

    }
}
