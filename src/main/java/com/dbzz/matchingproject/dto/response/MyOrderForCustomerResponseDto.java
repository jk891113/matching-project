package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Order;
import com.dbzz.matchingproject.entity.ShippingInfo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter

public class MyOrderForCustomerResponseDto {
    private long orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String shippingStatus;
    private ShippingInfoResponseDto shippingInfo;
    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    public MyOrderForCustomerResponseDto(Order order, ShippingInfo shippingInfo) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        this.shippingInfo = new ShippingInfoResponseDto(shippingInfo);
        this.shippingStatus = order.getShippingStatus().toString();
        List<OrderItemResponseDto> orderItemResponseDtos = order.getOrderItemList().stream()
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
        this.orderItemList = orderItemResponseDtos;

    }
}
