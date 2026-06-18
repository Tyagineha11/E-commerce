package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.LoginRequestDTO;
import com.example.e_commerce_website.dto.RegisterUserRequestDTO;
import com.example.e_commerce_website.dto.UpdateUserDTO;

public interface UserService {
	
	 ApiResponse registerUser(RegisterUserRequestDTO requestDTO);
	 
	 ApiResponse loginUser(LoginRequestDTO requestDTO);
	 
	 ApiResponse getAllUsers();
	 
	 ApiResponse updateUserProfile(String email, UpdateUserDTO requestDTO);
	 
	 ApiResponse changePassword(ChangePasswordDTO requestDTO);
	 
	 ApiResponse deleteUser(String email);
	 
	 ApiResponse getUserById(Long id);
	 
}
