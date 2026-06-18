package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceInterface.CartItemService;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ApiResponse getCartItems(
            @RequestParam Long userId) {

        return cartItemService
                .getCartItems(userId);
    }
}