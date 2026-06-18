package com.example.e_commerce_website.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
	
	@NotBlank(message = "Name is required")
    private String name;

    private String description;


}
