package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.ChatMessageRequestDto;
import com.dbzz.matchingproject.dto.response.ChatRoomResponseDto;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.ChatService;
import com.dbzz.matchingproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/customers/chatmessages/{orderItemId}")
    public ChatRoomResponseDto writeChatForCustomer(@PathVariable long orderItemId, @RequestBody ChatMessageRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatService.writeChatForCustomer(orderItemId, requestDto, userDetails.getUserId());
    }

    @PostMapping("/sellers/chatmessages/{orderId}")
    public ChatRoomResponseDto writeChatForSeller(@PathVariable long orderId, @RequestBody ChatMessageRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatService.writeChatForSeller(orderId, requestDto, userDetails.getUserId());
    }

    @GetMapping("/chatrooms/{roomId}")
    public ChatRoomResponseDto getChatRoom(@PathVariable long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatService.getChatRoom(roomId, userDetails.getUserId());
    }
}
