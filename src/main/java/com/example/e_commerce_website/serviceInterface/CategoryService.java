package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CategoryRequestDTO;

public interface CategoryService {
	
	ApiResponse createCategory(CategoryRequestDTO dto);
	
	ApiResponse getAllCategory();
	
	ApiResponse getCategoryById(Long Id);
	
	ApiResponse updateCategory(Long id, CategoryRequestDTO requestDTO);
	
	 ApiResponse deleteCategory(Long id);

}
