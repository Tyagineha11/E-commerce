package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CartDTO;
import com.example.e_commerce_website.serviceImpl.CartServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    // ADD TO CART
    @PostMapping("/add")
    public ApiResponse addToCart(@Valid @RequestBody CartDTO dto) {

        return cartService.addToCart(dto);
    }
    
 // GET CART
    @GetMapping
    public ApiResponse getCart(@RequestParam Long userId) {

        return cartService.getCart(userId);
    }

    // UPDATE CART
    @PutMapping("/update")
    public ApiResponse updateCart(@RequestBody CartDTO dto) {

        return cartService.updateCart(dto);
    }

    // REMOVE PRODUCT
    @DeleteMapping("/remove/{productId}")
    public ApiResponse removeFromCart(
            @PathVariable Long productId,
            @RequestParam Long userId) {

        return cartService.removeFromCart(userId, productId);
    }

    // CLEAR CART
    @DeleteMapping("/clear")
    public ApiResponse clearCart(@RequestParam Long userId) {

        return cartService.clearCart(userId);
    }
}