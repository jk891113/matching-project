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
public class OrderForSellerResponseDto {
    private long orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int totalAmount;
    private String shippingStatus;
    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    public OrderForSellerResponseDto(Order order, String sellerId) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        this.totalAmount = order.getTotalAmount();
        this.shippingStatus = order.getShippingStatus().getMessage();
        List<OrderItemResponseDto> orderItemResponseDtos = order.getOrderItemList().stream()
                .filter(orderItem -> orderItem.getSellerId().equals(sellerId))
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());

//        List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
//        for (OrderItem orderItem : order.getOrderItemList()) {
//            if (orderItem.getSellerId().equals(sellerId)) {
//                orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem));
//            }
//        }
        this.orderItemList = orderItemResponseDtos;
    }
}
