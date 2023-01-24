package com.dbzz.matchingproject.dto.response;

import com.dbzz.matchingproject.entity.ChatMessage;
import com.dbzz.matchingproject.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private long chatRoomId;
    private String sellerId;
    private String customerId;
    private List<ChatMessageResponseDto> messageResponseDtoList;

    public ChatRoomResponseDto(ChatRoom chatRoom, List<ChatMessage> chatMessageList) {
        this.chatRoomId = chatRoom.getRoomId();
        this.sellerId = chatRoom.getSellerId();
        this.customerId = chatRoom.getCustomerId();
        this.messageResponseDtoList = chatMessageList.stream()
                .map(ChatMessageResponseDto::new)
                .collect(Collectors.toList());
    }
}
