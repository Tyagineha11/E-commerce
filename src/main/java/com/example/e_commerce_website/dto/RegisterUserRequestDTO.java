package com.example.e_commerce_website.dto;

import java.time.LocalDate;

import com.example.e_commerce_website.entity.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserRequestDTO {
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid email format like example@gmail.com")
	private String email;
	
    @NotBlank(message = "New Password is required")
	@Pattern(
			 regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@._])[A-Za-z\\d@._]{6,10}$",
			 message="Password must contain uppercase, lowercase, number and @ . _"
			)
	private String password;
	
	@NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of Birth is required")
    private LocalDate dateOfBirth;
	
}
