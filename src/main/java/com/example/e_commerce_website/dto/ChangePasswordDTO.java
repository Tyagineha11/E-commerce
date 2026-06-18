package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangePasswordDTO {
	
	@NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Old Password is required")
    private String oldPassword;

    @NotBlank(message = "New Password is required")
    @Pattern(
    	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@._])[A-Za-z\\d@._]{6,}$",
    	    message = "Password must contain uppercase, lowercase, number and @ . _ (min 6 characters)"
    	)
    private String newPassword;

}
