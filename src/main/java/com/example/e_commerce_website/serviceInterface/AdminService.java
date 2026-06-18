package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.AdminRegisterDTO;
import com.example.e_commerce_website.dto.AdminloginDTO;
import com.example.e_commerce_website.dto.ApiResponse;

public interface AdminService {
	
	ApiResponse adminRegister(AdminRegisterDTO dto);
	
	ApiResponse loginAdmin(AdminloginDTO dto);
	
	ApiResponse getAllUsers();

    ApiResponse getAllSellers();
    
    ApiResponse getUserById(Long id);

    ApiResponse getSellerById(String id);

    ApiResponse getProductById(Long id);
}
