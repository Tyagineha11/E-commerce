package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceInterface.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // =========================
    // GET ITEMS BY ORDER ID
    // =========================
    @GetMapping("/{orderId}")
    public ApiResponse getItemsByOrderId(@PathVariable Long orderId) {
        return orderItemService.getItemsByOrderId(orderId);
    }

    // =========================
    // GET ITEMS BY SELLER ID
    // =========================
    @GetMapping("/seller/{sellerId}")
    public ApiResponse getItemsBySellerId(@PathVariable String sellerId) {
        return orderItemService.getItemsBySellerId(sellerId);
    }
    
	 // =========================
	 // UPDATE ORDER ITEM STATUS
	 // =========================
	 @PutMapping("/status/{id}")
	 public ApiResponse updateOrderItemStatus(
	         @PathVariable Long id,
	         @RequestParam String status
	 ) {
	     return orderItemService.updateOrderItemStatus(id, status);
	 }
}