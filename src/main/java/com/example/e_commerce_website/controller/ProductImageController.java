package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceInterface.ProductImageService;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageController {

    @Autowired
    private ProductImageService service;

    @PostMapping(value = "/upload/{productId}", consumes = "multipart/form-data")
    public ApiResponse uploadImages(
            @PathVariable Long productId,
            @RequestParam("files") MultipartFile[] files) {

        return service.uploadImages(productId, files);
    }

    @GetMapping("/product/{productId}")
    public ApiResponse getImagesByProductId(@PathVariable Long productId) {

        return service.getImagesByProductId(productId);
    }

    @DeleteMapping("/delete/{imageId}")
    public ApiResponse deleteImage(@PathVariable Long imageId) {

        return service.deleteImage(imageId);
    }
    
    @PutMapping(value = "/update/{imageId}", consumes = {"multipart/form-data"})
    public ApiResponse updateImage(
            @PathVariable Long imageId,
            @RequestPart("files") MultipartFile file) {

        return service.updateImage(imageId, file);
    }
}