package com.example.e_commerce_website.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderSummaryDTO {

    private Long id;
    private String orderNumber;
    private Double finalAmount;
    private String orderStatus;
    private LocalDateTime createdAt;
}