package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, String> {
    Optional<ShippingInfo> findByUserId(String userId);
    List<ShippingInfo> findAllByUserId(String userId);
    Optional<ShippingInfo> findByShippingInfoId(long shippingInfoId);
}
