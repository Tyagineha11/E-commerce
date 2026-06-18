package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;

public interface OrderItemService {

    ApiResponse getItemsByOrderId(Long orderId);

    ApiResponse getItemsBySellerId(String sellerId);
    
    ApiResponse updateOrderItemStatus(Long orderItemId, String status);
}
