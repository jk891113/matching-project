package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateOrderRequestDto;
import com.dbzz.matchingproject.dto.response.*;

import java.util.List;

public interface OrderService {
//    CreateOrderResponseDto createOrder(List<Long> productId, List<Integer> quantity, long shippingInfoId, String userId);

    CreateOrderResponseDto createOrder(CreateOrderRequestDto requestDto, String userId);

    MyOrderForCustomerResponseDto getOrderForCustomer(long orderId, String userId);

    List<OrderForCustomerResponseDto> getAllOrderForCustomer(String customerId);

    MyOrderForSellerResponseDto getOrderForSeller(long orderId, String sellerId);

    List<OrderForSellerResponseDto> getAllOrderForSeller(String sellerId);

    MyOrderForSellerResponseDto acceptOrder(long orderId, String sellerId);

    OrderItemResponseDto determineOrderItem(long orderId, String userId);
}
