package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CreateOrderRequestDto;
import com.dbzz.matchingproject.dto.response.*;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @PostMapping("/orders/{shippingInfoId}")
//    public ResponseEntity<StatusAndDataResponseDto> createOrder(@RequestParam List<Long> productId,
//                                              @RequestParam List<Integer> quantity,
//                                              @PathVariable long shippingInfoId,
//                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        CreateOrderResponseDto data =  orderService.createOrder(productId, quantity, shippingInfoId, userDetails.getUserId());
//        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 등록 완료", data);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
//        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
//    }

    @PostMapping("/orders")
    public ResponseEntity<StatusAndDataResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CreateOrderResponseDto data =  orderService.createOrder(requestDto, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 등록 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/customers/orders/{orderId}")
    public ResponseEntity<StatusAndDataResponseDto> getOrderForCustomer(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyOrderForCustomerResponseDto data = orderService.getOrderForCustomer(orderId, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/customers/orders")
    public ResponseEntity<StatusAndDataResponseDto> getAllOrderForCustomer(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<OrderForCustomerResponseDto> data = orderService.getAllOrderForCustomer(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/sellers/orders/{orderId}")
    public ResponseEntity<StatusAndDataResponseDto> getOrderForSeller(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyOrderForSellerResponseDto data = orderService.getOrderForSeller(orderId, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/sellers/orders")
    public ResponseEntity<StatusAndDataResponseDto> getAllOrderForSeller(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<OrderForSellerResponseDto> data = orderService.getAllOrderForSeller(userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PutMapping("/sellers/orders/{orderId}")
    public ResponseEntity<StatusAndDataResponseDto> acceptOrder(@PathVariable long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyOrderForSellerResponseDto data = orderService.acceptOrder(orderId, userDetails.getUserId());
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "주문 처리 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @PutMapping("/customers/orders/{orderItemId}")
    public void determineOrderItem(@PathVariable long orderItemId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.determineOrderItem(orderItemId, userDetails.getUserId());
    }
}
