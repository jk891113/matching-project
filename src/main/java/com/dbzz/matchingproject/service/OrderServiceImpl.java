package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateOrderRequestDto;
import com.dbzz.matchingproject.dto.response.*;
import com.dbzz.matchingproject.entity.*;
import com.dbzz.matchingproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ShippingInfoRepository shippingInfoRepository;
    private final PointRepository pointRepository;
    private final ChatRoomRepository chatRoomRepository;

//    @Override
//    @Transactional
//    public CreateOrderResponseDto createOrder(List<Long> productId, List<Integer> quantity, long shippingInfoId, String userId) {
//        int totalAmount = 0;
//        String sellerId = "";
//        Order order = new Order();
//        orderRepository.save(order);
//
//        List<OrderItem> orderItemList = new ArrayList<>();
//        for (int i = 0; i < productId.size(); i++) {
//            Product product = productRepository.findByProductId(productId.get(i)).orElseThrow(
//                    () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
//            );
//            sellerId = product.getUserId();
//            int amount = product.getPrice() * quantity.get(i);
//            totalAmount += amount;
//            OrderItem orderItem = new OrderItem(sellerId, product.getProductId(), quantity.get(i), amount, order);
//            orderItemList.add(orderItem);
//            orderItemRepository.save(orderItem);
//        }
//        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(shippingInfoId).orElseThrow(
//                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
//        );
//        order.putDatasInOrder(userId, sellerId, totalAmount, shippingInfoId);
//        orderRepository.save(order);
//        return new CreateOrderResponseDto(order, orderItemList, shippingInfo);
//    }

    @Override
    @Transactional
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto requestDto, String userId) {
        // Order 객체 미리 생성
        int totalAmount = 0;
        String sellerId = "";
        Order order = new Order();
        orderRepository.save(order);

        // OrderItem 객체 생성, 저장
        List<String> sellerIdList = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < requestDto.getProductId().size(); i++) {
            Product product = productRepository.findByProductId(requestDto.getProductId().get(i)).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
            );
            sellerId = product.getUserId();
            sellerIdList.add(sellerId);
            int amount = product.getPrice() * requestDto.getQuantity().get(i);
            totalAmount += amount;
            OrderItem orderItem = new OrderItem(sellerId, product.getProductId(), requestDto.getQuantity().get(i), amount, order);
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
        }

        // 배송정보, 포인트 체크
        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(requestDto.getShippingInfoId()).orElseThrow(
                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
        );
        Point point = pointRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("포인트가 존재하지 않습니다.")
        );

        // 대화방 생성
        List<ChatRoom> chatRoomList = sellerIdList.stream()
                .distinct()
                .map(s -> new ChatRoom(order.getOrderId(), s, userId))
                .collect(Collectors.toList());
        chatRoomRepository.saveAll(chatRoomList);

        // 포인트 차감, Order 객체 데이터 추가
        point.subtractPoint(requestDto.getPoint());
        order.putDatasInOrder(userId, sellerId, totalAmount - requestDto.getPoint(), requestDto.getShippingInfoId());
        orderRepository.save(order);
        return new CreateOrderResponseDto(order, orderItemList, shippingInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public MyOrderForCustomerResponseDto getOrderForCustomer(long orderId, String userId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다.")
        );
        if (!order.getCustomerId().equals(userId)) throw new IllegalArgumentException("본인의 주문 정보만 조회 가능합니다.");
        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(order.getShippingInfoId()).orElseThrow(
                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
        );
        return new MyOrderForCustomerResponseDto(order, shippingInfo);
    }

    @Override
    public List<OrderForCustomerResponseDto> getAllOrderForCustomer(String customerId) {
        List<OrderForCustomerResponseDto> responseDtos = orderRepository.findAllByCustomerId(customerId).stream()
                .map(order -> new OrderForCustomerResponseDto(order))
                .collect(Collectors.toList());
        return responseDtos;
    }


    @Override
    public MyOrderForSellerResponseDto getOrderForSeller(long orderId, String sellerId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다.")
        );
        ShippingInfo shippingInfo = shippingInfoRepository.findByShippingInfoId(order.getShippingInfoId()).orElseThrow(
                () -> new IllegalArgumentException("배송정보가 존재하지 않습니다.")
        );
        return new MyOrderForSellerResponseDto(order, sellerId, shippingInfo);
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
    public MyOrderForSellerResponseDto acceptOrder(long orderId, String sellerId) {
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

    @Override
    @Transactional
    public void determineOrderItem(long orderItemId, String userId) {
        OrderItem orderItem = orderItemRepository.findByItemId(orderItemId).orElseThrow(
                () -> new IllegalArgumentException("주문 상품이 존재하지 않습니다.")
        );
        orderItem.determineOrderItem();
        Point point = pointRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("포인트가 존재하지 않습니다.")
        );
        point.savingPoint(orderItem.getAmount());
        pointRepository.save(point);
        Order order = orderRepository.findByOrderId(orderItem.getOrder().getOrderId()).orElseThrow(
                () -> new IllegalArgumentException("주문 내역이 존재하지 않습니다.")
        );
        order.updateShippingStatus(order);
    }
}
