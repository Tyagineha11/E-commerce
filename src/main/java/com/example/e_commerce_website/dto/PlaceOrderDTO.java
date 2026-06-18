package com.example.e_commerce_website.dto;

import java.util.List;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderDTO {

	@NotNull(message = "UserId is required")
    private Long userId;
	
    @NotNull(message = "AddressId is required")
    private Long addressId;
    
    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double totalAmount;
    
    private Double shippingCharge;

    @NotNull(message = "Items cannot be null")
    private List<OrderItemDTO> items;
    
    private String paymentMethod;

    private String paymentStatus;

    private String paymentId;
}