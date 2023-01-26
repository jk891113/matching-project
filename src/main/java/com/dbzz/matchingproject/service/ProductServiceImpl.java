package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.request.UpdateProductRequestDto;
import com.dbzz.matchingproject.dto.response.AllProductResponseDto;
import com.dbzz.matchingproject.dto.response.ProductResponseDto;
import com.dbzz.matchingproject.entity.Product;
import com.dbzz.matchingproject.repository.ProductRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import com.dbzz.matchingproject.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
    public ProductResponseDto createProductPage(String userId, CreateProductRequestDto requestDto) {
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        Product product = new Product(userId, requestDto.getProductName(), requestDto.getPrice(), requestDto.getProductInfo(), (int) (requestDto.getPrice()*0.01));
        productRepository.save(product);
        return new ProductResponseDto(product);
    }

    //나의 판매상품 조회
    @Override
    public ProductResponseDto getProductByUserId(String userId, Long productId) {
        Product product= productRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException("productId값이 일치하는 상품이 없습니다.")
        );

        userRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("해당 회원이 등록한 상품이 아닙니다.")
        );

        ProductResponseDto productResponseDto = new ProductResponseDto(product);

        return productResponseDto;
    }

    //나의 전체 판매상품 조회
    @Override
    public List<ProductResponseDto> getAllProductByUserId(String userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        List<Product> list = productRepository.findByUserId(userId, pageable);
        List<ProductResponseDto> allProductByUserIdList = list.stream()
                .filter(product -> product.getUserId().equals(userId))
                .map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return allProductByUserIdList;
    }

    //전체 상품 조회(고객용)
    @Override
    public List<AllProductResponseDto> getAllProducts(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        List<Product> list = productRepository.findAllBy(pageable);
        List<AllProductResponseDto> allProductsList = list.stream().map(product -> new AllProductResponseDto(product)).collect(Collectors.toList());
        return allProductsList;
    }

    //판매상품 수정
    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long productId, UpdateProductRequestDto requestDto) {
        Product product = productRepository.findByProductId(productId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    //판매상품 삭제
    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );
//        if (product.getUserId().equals(user.getUserId())) {
            productRepository.deleteById(productId);
//        } throw new RuntimeException("본인이 등록한 상품만 삭제할 수 있습니다.");
    }
    // 판매상품 조회
    public List<ProductResponseDto> getSearchProducts(String keyword, Pageable pageable) {
        List<Product> list = productRepository.findByProductNameContaining(keyword, pageable);

        List<ProductResponseDto> allProductsList = list.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return allProductsList;
    }

//    public List<ProductResponseDto> getSearchProducts(String keyword, int page) {
//        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "modifiedAt"));
//        List<Product> list = productRepository.findByProductNameContaining(keyword, pageable);
//
//        List<ProductResponseDto> allProductsList = list.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
//        return allProductsList;
//    }
}
