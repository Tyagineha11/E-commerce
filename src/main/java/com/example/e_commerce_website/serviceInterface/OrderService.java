package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.PlaceOrderDTO;

import com.example.e_commerce_website.dto.ApiResponse;

public interface OrderService {

    ApiResponse placeOrder(PlaceOrderDTO dto);

    ApiResponse getAllOrders();

    ApiResponse getOrderById(Long id);

    ApiResponse cancelOrder(Long id);

    ApiResponse updateOrderStatus(Long id, String status);

    ApiResponse getOrdersByUserId(Long userId);
}