package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminloginDTO {
	
	@Email(message = "Enter valid email")
    @NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;

}
