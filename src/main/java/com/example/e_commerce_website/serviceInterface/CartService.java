package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CartDTO;

public interface CartService {

    ApiResponse addToCart(CartDTO cartDto);
    
    ApiResponse getCart(Long userId);

    ApiResponse updateCart(CartDTO dto);

    ApiResponse removeFromCart(Long userId, Long productId);

    ApiResponse clearCart(Long userId);
}