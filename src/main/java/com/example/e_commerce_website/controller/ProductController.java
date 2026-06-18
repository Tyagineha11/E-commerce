package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.e_commerce_website.dto.*;
import com.example.e_commerce_website.serviceInterface.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;  

    @PostMapping("/create")
    public ApiResponse create(@RequestBody ProductRequestDTO dto) {
        return service.createProduct(dto);
    }

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/getById/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse update(@PathVariable Long id,
                              @RequestBody ProductUpdateRequestDTO dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return service.deleteProduct(id);
    } 

    @GetMapping("/seller/{id}")
    public ApiResponse bySeller(@PathVariable String id) {
        return service.getProductsBySellerId(id);
    }
    
    @GetMapping("/category/{categoryId}")
    public ApiResponse byCategory(@PathVariable Long categoryId) {
        return service.getProductsByCategoryId(categoryId);
    }
}
