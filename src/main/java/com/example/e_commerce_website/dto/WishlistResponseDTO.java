package com.example.e_commerce_website.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WishlistResponseDTO {

    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal price;
    private String imageUrl;
}