package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageResponseDto {
    private String userId;
    private String message;
    private LocalDateTime createdAt;

    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.userId = chatMessage.getUserId();
        this.message = chatMessage.getMessage();
        this.createdAt = chatMessage.getCreatedAt();
    }
}
