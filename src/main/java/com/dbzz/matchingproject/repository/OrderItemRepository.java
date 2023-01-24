package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllBySellerId(String userId, Pageable pageable);
    List<OrderItem> findAllByOrderOrderId(long orderId);
    Optional<OrderItem> findByItemId(long orderItemId);
}
