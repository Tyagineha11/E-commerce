package com.example.e_commerce_website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
	
	private Integer code;
	private String message;
	private Object data;
	
	
}
