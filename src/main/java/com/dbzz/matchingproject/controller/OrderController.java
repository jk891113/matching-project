package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.response.OrderForCustomerResponseDto;
import com.dbzz.matchingproject.dto.response.OrderForSellerResponseDto;
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

    @PostMapping("/orders")
    public void createOrder(@RequestParam List<Long> productId, @RequestParam List<Integer> quantity,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.createOrder(productId, quantity, userDetails.getUserId());
    }

    @GetMapping("/orders/customers/{orderId}")
    public OrderForCustomerResponseDto getOrderForCustomer(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrderForCustomer(orderId);
    }

    @GetMapping("/orders/customers")
    public List<OrderForCustomerResponseDto> getAllOrderForCustomer(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getAllOrderForCustomer(userDetails.getUserId());
    }

    @GetMapping("/orders/sellers/{orderId}")
    public OrderForSellerResponseDto getOrderForSeller(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrderForSeller(orderId, userDetails.getUserId());
    }

    @GetMapping("/orders/sellers")
    public List<OrderForSellerResponseDto> getAllOrderForSeller(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getAllOrderForSeller(userDetails.getUserId());
    }

//    @GetMapping("/orders/items")
//    public List<OrderItemResponseDto> getOrderItemList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return orderService.getOrderItemList(userDetails.getUserId());
//    }

//    public void updateOrderItem() {
//
//    }
//
//    public void removeOrderItem() {
//
//    }

//    @PostMapping("/orders")
//    public void createOrder(@RequestBody OrderItemRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        orderService.createOrder(requestDto, userDetails.getUserId());
//    }

    @PutMapping("/orders/{orderId}")
    public void acceptOrder(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.acceptOrder(orderId);
    }
}
