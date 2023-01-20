package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.CreateOrderResponseDto;
import com.dbzz.matchingproject.dto.response.OrderForCustomerResponseDto;
import com.dbzz.matchingproject.dto.response.OrderForSellerResponseDto;
import com.dbzz.matchingproject.dto.response.OrderItemResponseDto;

import java.util.List;

public interface OrderService {
    CreateOrderResponseDto createOrder(List<Long> productId, List<Integer> quantity, long shippingInfoId, String userId);

    OrderForCustomerResponseDto getOrderForCustomer(long orderId);

    List<OrderForCustomerResponseDto> getAllOrderForCustomer(String customerId);

    OrderForSellerResponseDto getOrderForSeller(long orderId, String sellerId);

    List<OrderForSellerResponseDto> getAllOrderForSeller(String sellerId);

    OrderForSellerResponseDto acceptOrder(long orderId, String sellerId);
}
