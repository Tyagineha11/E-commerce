package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {

    private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private Double subtotal;
}