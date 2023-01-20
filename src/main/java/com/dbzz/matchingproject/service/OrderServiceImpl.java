package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.response.CreateOrderResponseDto;
import com.dbzz.matchingproject.dto.response.OrderForCustomerResponseDto;
import com.dbzz.matchingproject.dto.response.OrderForSellerResponseDto;
import com.dbzz.matchingproject.dto.response.OrderItemResponseDto;
import com.dbzz.matchingproject.entity.Order;
import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.entity.Product;
import com.dbzz.matchingproject.entity.ShippingInfo;
import com.dbzz.matchingproject.enums.ShippingStatusEnum;
import com.dbzz.matchingproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Override
//    @Transactional
    public CreateOrderResponseDto createOrder(List<Long> productId, List<Integer> quantity, String userId) {
        int totalAmount = 0;
        String sellerId = "";
        Order order = new Order();
        orderRepository.save(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < productId.size(); i++) {
            Product product = productRepository.findByProductId(productId.get(i)).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
            );
            sellerId = product.getUserId();
            int amount = product.getPrice() * quantity.get(i);
            totalAmount += amount;
            OrderItem orderItem = new OrderItem(sellerId, product.getProductId(), quantity.get(i), amount, order);
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
        }

        order.putDatasInOrder(userId, sellerId, totalAmount);
        orderRepository.save(order);
        return new CreateOrderResponseDto(order, orderItemList);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderForCustomerResponseDto getOrderForCustomer(long orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다.")
        );
        return new OrderForCustomerResponseDto(order);
    }

    @Override
    public List<OrderForCustomerResponseDto> getAllOrderForCustomer(String customerId) {
        List<OrderForCustomerResponseDto> responseDtos = orderRepository.findAllByCustomerId(customerId).stream()
                .map(order -> new OrderForCustomerResponseDto(order))
                .collect(Collectors.toList());
        return responseDtos;
    }


    @Override
    public OrderForSellerResponseDto getOrderForSeller(long orderId, String sellerId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다.")
        );
        return new OrderForSellerResponseDto(order, sellerId);
    }

    @Override
    public List<OrderForSellerResponseDto> getAllOrderForSeller(String sellerId) {
        List<Long> orderIdList = orderItemRepository.findAllBySellerId(sellerId).stream()
                .map(orderItem -> orderItem.getOrder().getOrderId())
                .collect(Collectors.toList());
        List<OrderForSellerResponseDto> responseDtos = orderRepository.findAllByOrderIdIn(orderIdList).stream()
                .map(order -> new OrderForSellerResponseDto(order, sellerId))
                .collect(Collectors.toList());
        return responseDtos;
    }

    @Override
    @Transactional
    public OrderForSellerResponseDto acceptOrder(long orderId, String sellerId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("주문 페이지가 존재하지 않습니다.")
        );
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderOrderId(orderId);
        for (OrderItem orderItem : orderItemList) {
            orderItem.acceptOrder(orderItem.getShippingStatus(), sellerId);
            orderItemRepository.save(orderItem);
        }
        order.updateShippingStatus(order);
        return getOrderForSeller(orderId, sellerId);
    }
}
