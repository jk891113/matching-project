package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;

public interface ProductService {
    void createProductPage(String userId, CreateProductRequestDto requestDto);

    void getAllProductByUserId();

    void getAllProducts();

    void updateProduct();

    void deleteProduct();
}
