package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.response.OrderItemResponseDto;
import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.entity.Product;
import com.dbzz.matchingproject.repository.OrderItemRepository;
import com.dbzz.matchingproject.repository.ProductRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void createOrderItem(Long productId, OrderItemRequestDto requestDto, String userId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
        int amount = product.getPrice() * requestDto.getQuantity();
        OrderItem orderItem = new OrderItem(userId, product.getProductId(), requestDto.getQuantity(), amount);
        orderItemRepository.save(orderItem);
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
        Product product = productRepository.findByProductId(requestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
        int amount = product.getPrice() * requestDto.getQuantity();
        OrderItem orderItem = new OrderItem(userId, requestDto.getProductId(), requestDto.getQuantity(), amount);

    }

    @Override
    public void getOrderByUserId() {

    }

    @Override
    public void acceptOrder() {

    }
}
