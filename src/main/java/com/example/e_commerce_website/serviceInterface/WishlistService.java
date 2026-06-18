package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;

public interface WishlistService {

    ApiResponse addToWishlist(Long userId, Long productId);

    ApiResponse getWishlistByUser(Long userId);

    ApiResponse removeFromWishlist(Long userId, Long productId);
}