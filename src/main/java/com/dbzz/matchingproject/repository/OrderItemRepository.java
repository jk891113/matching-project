package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllBySellerId(String userId);
    List<OrderItem> findAllByOrderOrderId(long orderId);
}
