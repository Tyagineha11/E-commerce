package com.example.e_commerce_website.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.e_commerce_website.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private Gender gender;
	
	private LocalDate dateOfBirth;
	
	private LocalDateTime createdAt;
	
}
