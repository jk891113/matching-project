package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final JwtUtil jwtUtil;

    //판매상품 등록
    @PostMapping("/products/{userId}")
    public ResponseEntity<StatusResponseDto> createProductPage(@PathVariable String userId, @RequestBody CreateProductRequestDto requestDto, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "상품 등록 완료");
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        productService.createProductPage(userId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
