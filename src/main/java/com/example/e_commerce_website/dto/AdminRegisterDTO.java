package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminRegisterDTO {
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	 @NotBlank(message = "Email is required")
	    @Pattern(
	        regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
	        message = "Email must be a valid @gmail.com address"
	    )
	private String email;
	 
	@Pattern(
		   regexp = "^[0-9]{10}$",
		   message = "Phone must be 10 digits"
	)
	private String phone;
	
	@NotBlank(message = "Password is required")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@._])[A-Za-z\\d@._]{6,}$",
        message = "Password must contain uppercase, lowercase, number, and one special character (@ . _) with minimum 6 characters"
    )
	private String password;

}
