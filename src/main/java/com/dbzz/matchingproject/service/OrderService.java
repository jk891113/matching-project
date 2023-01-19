package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.response.OrderItemResponseDto;

import java.util.List;

public interface OrderService {
    void createOrderItem(Long productId, OrderItemRequestDto requestDto, String userId);

    List<OrderItemResponseDto> getOrderItemList(String userId);

    void createOrder(OrderItemRequestDto requestDto, String userId);

    void getOrderByUserId();

    void acceptOrder();
}
