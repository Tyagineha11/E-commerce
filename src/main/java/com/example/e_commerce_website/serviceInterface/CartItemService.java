package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;

public interface CartItemService {

    ApiResponse getCartItems(Long userId);

}