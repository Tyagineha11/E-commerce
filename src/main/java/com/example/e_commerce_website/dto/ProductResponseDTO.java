package com.example.e_commerce_website.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.e_commerce_website.entity.ProductStatus;
import com.example.e_commerce_website.entity.Stock;

import lombok.Data;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Stock stock;
    private String brand; 
    private String color;
    private Double weight;
    private String size;
    private ProductStatus status;

    private String sellerName;
    private String categoryName;

    private LocalDateTime createdAt;
    private String imageBase64;
}