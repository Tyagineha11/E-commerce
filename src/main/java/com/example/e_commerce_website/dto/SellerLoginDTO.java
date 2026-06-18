package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SellerLoginDTO {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
