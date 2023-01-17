package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.entity.Product;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.repository.ProductRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //판매상품 등록
    @Override
    public void createProductPage(String userId, CreateProductRequestDto requestDto) {
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        Product product = new Product(userId, requestDto.getProductName(), requestDto.getPrice(), requestDto.getProductInfo());
        productRepository.save(product);
    }

    //나의 판매상품 조회
    @Override
    public List<ProductResponseDto> getAllProductByUserId(String userId) {
        List<Product> list = productRepository.findByUserId(userId);

        List<ProductResponseDto> allProductByUserIdList = list.stream().map(product -> new ProductResponseDto(userId, product)).collect(Collectors.toList());
        return allProductByUserIdList;
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
