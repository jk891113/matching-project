package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
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
    @Transactional
    public void createOrderItem(List<Long> productId, List<Integer> quantity, String userId) {
        int totalAmount = 0;
        for (int i = 0; i < productId.size(); i++) {
            Product product = productRepository.findByProductId(productId.get(i)).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
            );
            int amount = product.getPrice() * quantity.get(i);
            totalAmount += amount;
            OrderItem orderItem = new OrderItem(userId, product.getProductId(), quantity.get(i), amount);
            orderItemRepository.save(orderItem);
        }
        Order order = new Order(userId, totalAmount, ShippingStatusEnum.DEFAULT);
        orderRepository.save(order);
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemList(String userId) {
        List<OrderItem> orderItemList = orderItemRepository.findAllByCustomerId(userId);
        List<OrderItemResponseDto> responseDtos = orderItemList.stream()
                .map(orderItem -> new OrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
        return responseDtos;
    }

    @Override
    @Transactional
    public void createOrder(OrderItemRequestDto requestDto, String userId) {

    }

    @Override
    public void getOrderByUserId() {

    }

    @Override
    public void acceptOrder(long orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("주문 페이지가 존재하지 않습니다.")
        );
    }
}
