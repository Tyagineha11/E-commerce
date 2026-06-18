package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SellerRegisterDTO {

    @NotBlank(message = "Owner Name is required")
    private String ownerName;

    @NotBlank(message = "Shop Name is required")
    private String shopName;

    @NotBlank(message = "Email is required")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
        message = "Email must be a valid @gmail.com address"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@._])[A-Za-z\\d@._]{6,}$",
        message = "Password must contain uppercase, lowercase, number, and one special character (@ . _) with minimum 6 characters"
    )
    private String password;

    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone must be 10 digits"
    )
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @Pattern(
        regexp = "^[0-9]{6}$",
        message = "Pincode must be 6 digits"
    )
    private String pincode;
}
