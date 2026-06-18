package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ProductRequestDTO;
import com.example.e_commerce_website.dto.ProductUpdateRequestDTO;

public interface ProductService {
	
	//Create Product
	ApiResponse createProduct(ProductRequestDTO dto);
	
	//get Product
	ApiResponse getAllProducts();
	
	//get Product By Id
	ApiResponse getProductById(Long id);
	
	//Update Product
	ApiResponse updateProduct(Long id, ProductUpdateRequestDTO dto);
	
	//delete Product
	ApiResponse deleteProduct(Long id);
	
	//get product By Id
	ApiResponse getProductsByCategoryId(Long categoryId);
	

	ApiResponse getProductsBySellerId(String sellerId);

	
	
	

}
