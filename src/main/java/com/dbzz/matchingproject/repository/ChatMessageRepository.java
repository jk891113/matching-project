package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatRoomId(long roomId);
}
