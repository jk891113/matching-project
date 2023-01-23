package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByOrderIdAndSellerId(long orderId, String sellerId);
    Optional<ChatRoom> findByRoomId(long roomId);
}
