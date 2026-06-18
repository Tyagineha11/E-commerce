package com.example.e_commerce_website.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private String orderNumber;

    private Long userId;
    private Long addressId;

    private Double totalAmount;
    private Double shippingCharge;
    private Double finalAmount;

    private String orderStatus;
    private String paymentStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<OrderItemResponseDTO> items;
}