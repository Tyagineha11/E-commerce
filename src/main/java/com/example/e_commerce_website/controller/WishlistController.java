package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceInterface.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // ADD TO WISHLIST
    @PostMapping("add/{productId}")
    public ApiResponse addToWishlist(
            @RequestHeader Long userId,
            @PathVariable Long productId) {

        return wishlistService.addToWishlist(userId, productId);
    }

    // GET WISHLIST
    @GetMapping("/get")
    public ApiResponse getWishlist(@RequestHeader Long userId) {

        return wishlistService.getWishlistByUser(userId);
    }

    // REMOVE FROM WISHLIST
    @DeleteMapping("delete/{productId}")
    public ApiResponse removeFromWishlist(
            @RequestHeader Long userId,
            @PathVariable Long productId) {

        return wishlistService.removeFromWishlist(userId, productId);
    }
}