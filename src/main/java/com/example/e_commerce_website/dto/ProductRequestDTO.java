package com.example.e_commerce_website.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.e_commerce_website.entity.ProductStatus;
import com.example.e_commerce_website.entity.Stock;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @Size(max = 2000)
    private String description;

    @Size(max = 255)
    private String shortDescription;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @DecimalMin("0.0")
    private BigDecimal discountPrice;

    @NotNull
    private Stock stock;

    private String brand;

    private Double weight;

    private String color;

    private String size;
    
    private List<String> images;

    @NotNull
    private ProductStatus status;

    // 🔥 FIXED
    @NotBlank(message = "Seller ID required")
    private String sellerId;

    @NotNull(message = "Category ID required")
    private Long categoryId;
}