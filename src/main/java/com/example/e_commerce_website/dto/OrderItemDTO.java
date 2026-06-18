package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long productId;
    private Integer quantity;
    private Double price;
    private Double subtotal;
    private String status;
}