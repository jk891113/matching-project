package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.request.UpdateProductRequestDto;
import com.dbzz.matchingproject.dto.response.AllProductResponseDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    //나의 판매상품 조회
    @GetMapping("/products/{userId}/{productId}")
    public ProductResponseDto getProductByUserId(@PathVariable String userId, @PathVariable Long productId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return productService.getProductByUserId(userId, productId);
    }

    //나의 전체 판매상품 조회
    @GetMapping("/products/{userId}")
    public List<ProductResponseDto> getAllProductByUserId(@PathVariable String userId, HttpServletRequest request, Pageable pageable) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return productService.getAllProductByUserId(userId, pageable);
    }

    //전체 상품 조회(고객용)
    @GetMapping("/products")
    public List<AllProductResponseDto> getAllProducts(Pageable pageable, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        return productService.getAllProducts(pageable);
    }

    //판매상품 수정
    @PutMapping("/products/{productId}")
    public ResponseEntity<StatusResponseDto> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequestDto requestDto, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "상품 수정 완료");
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        productService.updateProduct(productId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //판매상품 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<StatusResponseDto> deleteProduct(@PathVariable Long productId, HttpServletRequest request) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "상품 삭제 완료");
        String token = jwtUtil.resolveToken(request);
        jwtUtil.validateAndGetUserInfo(token);
        productService.deleteProduct(productId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
