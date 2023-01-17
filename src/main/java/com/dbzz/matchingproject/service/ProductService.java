package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.dto.response.StatusResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    void createProductPage(String userId, CreateProductRequestDto requestDto);

    List<ProductResponseDto> getAllProductByUserId(String userId);

    void getAllProducts();

    void updateProduct();

    void deleteProduct();
}
