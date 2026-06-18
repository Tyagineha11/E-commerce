package com.example.e_commerce_website.dto;

import java.time.LocalDate;

import com.example.e_commerce_website.entity.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDTO {
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "Phone is Required")
	private String phone;
	
	@NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of Birth is required")
    private LocalDate dateOfBirth;
	
}
