package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Order;
import com.dbzz.matchingproject.entity.ShippingInfo;
import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MyOrderForSellerResponseDto {
    private long orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int totalAmount;
    private String shippingStatus;
    private ShippingInfoResponseDto shippingInfo;
    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    public MyOrderForSellerResponseDto(Order order, String sellerId, ShippingInfo shippingInfo) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        this.totalAmount = order.getTotalAmount();
        this.shippingStatus = order.getShippingStatus().getMessage();
        this.shippingInfo = new ShippingInfoResponseDto(shippingInfo);
        List<OrderItemResponseDto> orderItemResponseDtos = order.getOrderItemList().stream()
                .filter(orderItem -> orderItem.getSellerId().equals(sellerId))
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
        this.orderItemList = orderItemResponseDtos;
    }
}
