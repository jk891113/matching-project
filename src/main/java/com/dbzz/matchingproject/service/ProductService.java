package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.request.UpdateProductRequestDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    //판매상품 등록
    void createProductPage(String userId, CreateProductRequestDto requestDto);

    //나의 판매상품 조회
    ProductResponseDto getProductByUserId(String userId, Long productId);

    //나의 전체 판매상품 조회
    List<ProductResponseDto> getAllProductByUserId(String userId);

    //전체 상품 조회(고객용)
    List<ProductResponseDto> getAllProducts();

    //판매상품 수정
    ProductResponseDto updateProduct(Long productId, UpdateProductRequestDto requestDto);

    //판매상품 삭제
    void deleteProduct(Long productId);
}
