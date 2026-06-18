package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {
	
	private Long id;
	private String name;
    private String description;
    private Boolean isActive;

}
