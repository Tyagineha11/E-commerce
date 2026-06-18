package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SellerUpdateDTO {
	
	@NotBlank(message = "Owner Name is required")
    private String ownerName;

    @NotBlank(message = "Shop Name is required")
    private String shopName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

}
