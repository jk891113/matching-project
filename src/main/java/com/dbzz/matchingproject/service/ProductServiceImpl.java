package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.entity.Product;
import com.dbzz.matchingproject.repository.ProductRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void createProductPage(String userId, CreateProductRequestDto requestDto) {
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        Product product = new Product(userId, requestDto.getProductName(), requestDto.getPrice(), requestDto.getProductInfo());
        productRepository.save(product);
    }

    @Override
    public void getAllProductByUserId() {

    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void updateProduct() {

    }

    @Override
    public void deleteProduct() {

    }
}
