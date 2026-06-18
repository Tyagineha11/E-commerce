package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.AdminRegisterDTO;
import com.example.e_commerce_website.dto.AdminloginDTO;
import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceInterface.AdminService;
import com.example.e_commerce_website.serviceInterface.CategoryService;
import com.example.e_commerce_website.serviceInterface.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	//REGISTER API
	@PostMapping("/register")
	public ApiResponse adminRegister(
	        @Valid @RequestBody AdminRegisterDTO dto) {

	    return adminService.adminRegister(dto);
	}
	
	// LOGIN API
	@PostMapping("/login")

	public ApiResponse loginAdmin(
	        @Valid @RequestBody AdminloginDTO requestDTO) {

	    return adminService.loginAdmin(requestDTO);
	}
	
	@GetMapping("/users")
	public ApiResponse getAllUsers() {
	    return adminService.getAllUsers();
	}

	@GetMapping("/sellers")
	public ApiResponse getAllSellers() {
	    return adminService.getAllSellers();
	}
	
	@GetMapping("/products")
	public ApiResponse getAllProducts() {
	    return productService.getAllProducts();
	}
	
	@GetMapping("/categories")
	public ApiResponse getAllCategories() {
	    return categoryService.getAllCategory();
	}
	
	
	@GetMapping("/users/{id}")
	public ApiResponse getUserById(@PathVariable Long id){
	    return adminService.getUserById(id);
	}
	
	@GetMapping("/seller/{id}")
	public ApiResponse getSellerById(@PathVariable String id){
	    return adminService.getSellerById(id);
	}
	
	@GetMapping("/products/{id}")
	public ApiResponse getProductById(@PathVariable Long id){
	    return productService.getProductById(id);
	}
 
}
