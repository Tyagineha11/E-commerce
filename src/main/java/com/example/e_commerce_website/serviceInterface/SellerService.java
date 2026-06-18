package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.SellerLoginDTO;
import com.example.e_commerce_website.dto.SellerRegisterDTO;
import com.example.e_commerce_website.dto.SellerUpdateDTO;

public interface SellerService {
	
	ApiResponse registerSeller(SellerRegisterDTO registerDTO);
	
	ApiResponse loginSeller(SellerLoginDTO registerDTO);
	
	ApiResponse getAllSellers();
	
	ApiResponse getSellerProfile(String email);
	
	ApiResponse updateSellerProfile(String email, SellerUpdateDTO dto);
	
	ApiResponse changePassword(ChangePasswordDTO requestDTO);

}
