package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Enter Valid Email")
	private String email;
	
	
	@NotBlank(message = "Password is Required")
	private String password;
}
