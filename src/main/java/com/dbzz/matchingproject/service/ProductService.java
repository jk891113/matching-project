package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.entity.User;

public interface ProductService {
    void createProductPage(String userId, CreateProductRequestDto requestDto);

    void getAllProductByUserId();

    void getAllProducts();

    void updateProduct();

    void deleteProduct();
}
