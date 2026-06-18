package com.example.e_commerce_website.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartResponseDTO {

    private Long cartId;

    private Long userId;

    private List<CartItemResponseDTO> items;

    private Double totalAmount;
}