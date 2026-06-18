    package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.LoginRequestDTO;
import com.example.e_commerce_website.dto.RegisterUserRequestDTO;
import com.example.e_commerce_website.dto.UpdateUserDTO;
import com.example.e_commerce_website.serviceInterface.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	

    // Register User API
    @PostMapping("/register")
    public ApiResponse registerUser(@Valid @RequestBody RegisterUserRequestDTO requestDTO) {
        return userService.registerUser(requestDTO);
    }
    
    //Login User API
    @PostMapping("/login")
    public ApiResponse loginUser(@Valid @RequestBody LoginRequestDTO requestDTO) {
    	return userService.loginUser(requestDTO);
    }
    
    //get User API
    @GetMapping("/getUser")
    public ApiResponse getAllUsers() {
    	return userService.getAllUsers(); 
    }
    
    //update user API
    @PutMapping("/update/{email}")
    public ApiResponse updateUser(
            @PathVariable String email,
            @Valid @RequestBody UpdateUserDTO requestDTO) {

        return userService.updateUserProfile(email, requestDTO);
    }
    
    //change passwOrd API
    @PutMapping("/change-password")
    public ApiResponse changePassword(
            @Valid @RequestBody ChangePasswordDTO requestDTO) {

        return userService.changePassword(requestDTO);
    }
    
    //delete user API
    @DeleteMapping("/delete/{email}")
    public ApiResponse deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }
    
    @GetMapping("/get/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
}
