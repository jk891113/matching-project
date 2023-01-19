package com.dbzz.matchingproject.dto.request;

import com.dbzz.matchingproject.entity.OrderItem;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class OrderItemRequestDto {
    private long productId;
    private int quantity;
//
//    private Map<Long, Integer> orderItemList = new HashMap<>();
}
