package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.Order;
import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CreateOrderResponseDto {
    private long orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int totalAmount;
    private String shippingStatus;
    private ShippingInfoResponseDto shippingInfo;
    private List<OrderItemResponseDto> orderItemList = new ArrayList<>();

    public CreateOrderResponseDto(Order order, List<OrderItem> orderItemList, ShippingInfo shippingInfo) {
        this.orderId = order.getOrderId();
        this.customerId = order.getCustomerId();
        this.createdAt = order.getCreatedAt();
        this.modifiedAt = order.getModifiedAt();
        this.totalAmount = order.getTotalAmount();
        this.shippingStatus = order.getShippingStatus().getMessage();
        this.shippingInfo = new ShippingInfoResponseDto(shippingInfo);
        List<OrderItemResponseDto> orderItemResponseDtos = orderItemList.stream()
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
        this.orderItemList = orderItemResponseDtos;

    }


}
