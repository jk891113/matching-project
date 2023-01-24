package com.dbzz.matchingproject.controller;

import com.dbzz.matchingproject.dto.request.CreateProductRequestDto;
import com.dbzz.matchingproject.dto.request.UpdateProductRequestDto;
import com.dbzz.matchingproject.dto.response.*;
import com.dbzz.matchingproject.enums.StatusEnum;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
public class ProductController {
    private final ProductService productService;

    //판매상품 등록
    @PostMapping("/products")
    public ResponseEntity<StatusAndDataResponseDto> createProductPage(@RequestBody CreateProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProductResponseDto data = productService.createProductPage(userDetails.getUserId(), requestDto);
        StatusAndDataResponseDto responseDto = new  StatusAndDataResponseDto(StatusEnum.OK, "상품 등록 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //나의 판매상품 조회
    @GetMapping("/products/my/{productId}")
    public ResponseEntity<StatusAndDataResponseDto> getProductByUserId(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProductResponseDto data = productService.getProductByUserId(userDetails.getUserId(), productId);
        StatusAndDataResponseDto responseDto = new  StatusAndDataResponseDto(StatusEnum.OK, "상품 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    // 페이징
    //나의 전체 판매상품 조회
    @GetMapping("/products/{userId}")
    public ResponseEntity<StatusAndDataResponseDto> getAllProductByUserId(@PathVariable String userId, Pageable pageable) {
        List<ProductResponseDto> data = productService.getAllProductByUserId(userId, pageable);
        StatusAndDataResponseDto responseDto = new  StatusAndDataResponseDto(StatusEnum.OK, "나의 전체 판매상품 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }


    // 페이징
    //전체 상품 조회(고객용)
    @GetMapping("/customers/products")
    public ResponseEntity<StatusAndDataResponseDto> getAllProducts(Pageable pageable) {
        List<AllProductResponseDto> data = productService.getAllProducts(pageable);
        StatusAndDataResponseDto responseDto = new  StatusAndDataResponseDto(StatusEnum.OK, "전체 상품 조회 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //판매상품 수정
    @PutMapping("/products/{productId}")
    public ResponseEntity<StatusAndDataResponseDto> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequestDto requestDto) {
        ProductResponseDto data = productService.updateProduct(productId, requestDto);
        StatusAndDataResponseDto responseDto = new StatusAndDataResponseDto(StatusEnum.OK, "상품 수정 완료", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    //판매상품 삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<StatusResponseDto> deleteProduct(@PathVariable Long productId) {
        StatusResponseDto responseDto = new StatusResponseDto(StatusEnum.OK, "상품 삭제 완료");
        productService.deleteProduct(productId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //상품 검색
    @GetMapping("/search/products")
    public ResponseEntity<List<ProductResponseDto>> searchProduct(@RequestParam String keyword, @PageableDefault(size = 10, sort = "productId", direction = Sort.Direction.DESC) Pageable pageable) {

        List<ProductResponseDto> searchProducts = productService.getSearchProducts(keyword, pageable);
        return new ResponseEntity<>(searchProducts,HttpStatus.OK);
    }

//    @GetMapping("/search/products")
//    public ResponseEntity<List<ProductResponseDto>> searchProduct(@RequestParam String keyword, @RequestParam int page) {
//
//        List<ProductResponseDto> searchProducts = productService.getSearchProducts(keyword, page);
//        return new ResponseEntity<>(searchProducts,HttpStatus.OK);
//    }



}
