package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.response.OrderItemResponseDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
//    private final JwtUtil jwtUtil;

//    @PostMapping("/orders/items/{productId}")
//    public void createOrderItem(@PathVariable Long productId,
//                                @RequestBody OrderItemRequestDto requestDto,
//                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
////        String token = jwtUtil.resolveToken(request);
////        jwtUtil.validateAndGetUserInfo(token);
//        orderService.createOrderItem(productId, requestDto, userDetails.getUserId());
//    }
//
    @GetMapping("/orders/items")
    public List<OrderItemResponseDto> getOrderItemList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrderItemList(userDetails.getUserId());
    }

    public void updateOrderItem() {

    }

    public void removeOrderItem() {

    }

    @PostMapping("/Orders")
    public void createOrder(@RequestBody OrderItemRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.createOrder(requestDto, userDetails.getUserId());
    }
}
