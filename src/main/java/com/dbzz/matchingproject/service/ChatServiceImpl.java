package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.ChatMessageRequestDto;
import com.dbzz.matchingproject.dto.response.ChatRoomResponseDto;
import com.dbzz.matchingproject.entity.ChatMessage;
import com.dbzz.matchingproject.entity.ChatRoom;
import com.dbzz.matchingproject.entity.OrderItem;
import com.dbzz.matchingproject.repository.ChatMessageRepository;
import com.dbzz.matchingproject.repository.ChatRoomRepository;
import com.dbzz.matchingproject.repository.OrderItemRepository;
import com.dbzz.matchingproject.service.interfaces.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final OrderItemRepository orderItemRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void createChatRoom(List<String> sellerIdList, long orderId, String customerId) {
        List<ChatRoom> chatRoomList = sellerIdList.stream()
                .distinct()
                .map(s -> new ChatRoom(orderId, s, customerId))
                .collect(Collectors.toList());
        chatRoomRepository.saveAll(chatRoomList);
    }

    @Override
    public ChatRoomResponseDto writeChatForCustomer(long orderItemId, ChatMessageRequestDto requestDto, String userId) {
        OrderItem orderItem = orderItemRepository.findByItemId(orderItemId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문 상품 내역이 없습니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findByOrderIdAndSellerId(orderItem.getOrder().getOrderId(), orderItem.getSellerId()).orElseThrow(
                () -> new IllegalArgumentException("대화방이 존재하지 않습니다.")
        );
        if (chatRoom.isOn()) {
            ChatMessage chatMessage = new ChatMessage(chatRoom.getRoomId(), userId, requestDto.getMessage());
            chatMessageRepository.save(chatMessage);
        } else throw new IllegalArgumentException("대화방이 종료 되었습니다.");

        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatRoomId(chatRoom.getRoomId());
        return new ChatRoomResponseDto(chatRoom, chatMessageList);
    }

    @Override
    public ChatRoomResponseDto writeChatForSeller(long orderId, ChatMessageRequestDto requestDto, String userId) {
        ChatRoom chatRoom = chatRoomRepository.findByOrderIdAndSellerId(orderId, userId).orElseThrow(
                () -> new IllegalArgumentException("대화방이 존재하지 않습니다.")
        );
        if (chatRoom.isOn()) {
            ChatMessage chatMessage = new ChatMessage(chatRoom.getRoomId(), userId, requestDto.getMessage());
            chatMessageRepository.save(chatMessage);
        } else throw new IllegalArgumentException("대화방이 종료 되었습니다.");

        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatRoomId(chatRoom.getRoomId());
        return new ChatRoomResponseDto(chatRoom, chatMessageList);
    }

    @Override
    public ChatRoomResponseDto getChatRoom(long roomId, String userId) {
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId).orElseThrow(
                () -> new IllegalArgumentException("대화방이 존재하지 않습니다.")
        );
        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatRoomId(roomId);
        return new ChatRoomResponseDto(chatRoom, chatMessageList);
    }
}
