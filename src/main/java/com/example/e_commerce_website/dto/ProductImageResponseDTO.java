package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class ProductImageResponseDTO {

    private Long id;

    private String imageName;

    private Long productId;

    private String imageBase64;
}