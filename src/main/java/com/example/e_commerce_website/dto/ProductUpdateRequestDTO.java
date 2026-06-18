package com.example.e_commerce_website.dto;

import java.math.BigDecimal;
import com.example.e_commerce_website.entity.ProductStatus;
import com.example.e_commerce_website.entity.Stock;
import lombok.Data;

@Data
public class ProductUpdateRequestDTO {

    private String name;
    private String description;
    private String shortDescription;

    private BigDecimal price;
    private BigDecimal discountPrice;

    private Double weight;

    private Stock stock;
    private ProductStatus status;

    private String brand;
    private String color;
    private String size;

    private String sellerId;
    private Long categoryId;
}