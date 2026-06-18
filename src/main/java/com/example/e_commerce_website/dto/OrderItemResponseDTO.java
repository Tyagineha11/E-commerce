package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {

	    private Long orderId;

	    private Long productId;

	    private String sellerId;

	    private Integer quantity;

	    private Double price;

	    private Double subtotal;

	    private String status;
	}