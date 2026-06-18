package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.PlaceOrderDTO;
import com.example.e_commerce_website.serviceInterface.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // =========================
    // PLACE ORDER
    // =========================
    @PostMapping("/place")
    public ApiResponse placeOrder(@Valid @RequestBody PlaceOrderDTO dto) {
        return orderService.placeOrder(dto);
    }

    // =========================
    // GET ALL ORDERS
    // =========================
    @GetMapping("/all")
    public ApiResponse getAllOrders() {
        return orderService.getAllOrders();
    }

    // =========================
    // GET ORDER BY ID
    // =========================
    @GetMapping("get/{id}")
    public ApiResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // =========================
    // GET ORDERS BY USER ID
    // =========================
    @GetMapping("/user/{userId}")
    public ApiResponse getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // =========================
    // CANCEL ORDER
    // =========================
    @PutMapping("/cancel/{id}")
    public ApiResponse cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    // =========================
    // UPDATE ORDER STATUS
    // =========================
    @PutMapping("/status/{id}")
    public ApiResponse updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return orderService.updateOrderStatus(id, status);
    }
}