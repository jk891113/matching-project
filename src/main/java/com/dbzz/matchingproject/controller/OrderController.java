package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.OrderItemRequestDto;
import com.dbzz.matchingproject.dto.request.ShippingInfoRequestDto;
import com.dbzz.matchingproject.dto.response.CreateOrderResponseDto;
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

    @PostMapping("/orders/{shippingInfoId}")
    public CreateOrderResponseDto createOrder(@RequestParam List<Long> productId,
                                              @RequestParam List<Integer> quantity,
                                              @PathVariable long shippingInfoId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.createOrder(productId, quantity, shippingInfoId, userDetails.getUserId());
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

    @PutMapping("/orders/{orderId}")
    public OrderForSellerResponseDto acceptOrder(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.acceptOrder(orderId, userDetails.getUserId());
    }
}
