package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
