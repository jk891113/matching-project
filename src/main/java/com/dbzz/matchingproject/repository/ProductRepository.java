package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(String userId);

    List<Product> findAll();

    Optional<Product> findByProductId(Long productId);
    List<Product> findByProductNameContaining(String keyword, Pageable pageable);
}
