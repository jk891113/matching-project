package com.dbzz.matchingproject.service.interfaces;

import com.dbzz.matchingproject.dto.request.ChatMessageRequestDto;
import com.dbzz.matchingproject.dto.response.ChatRoomResponseDto;

import java.util.List;

public interface ChatService {

    void createChatRoom(List<String> sellerIdList, long orderId, String customerId);

    ChatRoomResponseDto writeChatForCustomer(long orderItemId, ChatMessageRequestDto requestDto, String userId);

    ChatRoomResponseDto writeChatForSeller(long orderId, ChatMessageRequestDto requestDto, String userId);

    ChatRoomResponseDto getChatRoom(long roomId, String userId);
}
