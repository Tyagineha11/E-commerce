package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.SellerLoginDTO;
import com.example.e_commerce_website.dto.SellerRegisterDTO;
import com.example.e_commerce_website.dto.SellerUpdateDTO;
import com.example.e_commerce_website.serviceInterface.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	
	//REGISTER API
	@PostMapping("/register")
    public ResponseEntity<ApiResponse> registerSeller(
            @Valid @RequestBody SellerRegisterDTO registerDTO,
            BindingResult result) {

        if (result.hasErrors()) {

            String errorMessage = result.getFieldError().getDefaultMessage();

            ApiResponse response =  new ApiResponse(400, errorMessage, null);

            return ResponseEntity.badRequest().body(response);
        }

        ApiResponse response = sellerService.registerSeller(registerDTO);

        return ResponseEntity.status(response.getCode()).body(response);
    }
	
	//LOGIN API
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginSeller(@Valid @RequestBody SellerLoginDTO loginDTO, 
			BindingResult result) {

        if (result.hasErrors()) {

            String errorMessage = result.getFieldError().getDefaultMessage();

            ApiResponse response = new ApiResponse(400, errorMessage, null);

            return ResponseEntity.badRequest().body(response);
        }

        ApiResponse response = sellerService.loginSeller(loginDTO);

        return ResponseEntity.status(response.getCode()).body(response);
    }
	
	//FETCH all API
	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse> getAllSellers() {

	    ApiResponse response = sellerService.getAllSellers();

	    return ResponseEntity.status(response.getCode()).body(response);
	}
	
	//FETCH email API
	@GetMapping("/profile")
	public ResponseEntity<ApiResponse> getSellerProfile(
			 @RequestParam String email) {

	    ApiResponse response = sellerService.getSellerProfile(email);

	    return ResponseEntity.status(response.getCode()).body(response);
	}
	
	//UPDATE API
	@PutMapping("/profile")
	public ResponseEntity<ApiResponse> updateSellerProfile(
	        @RequestParam String email,
	        @Valid @RequestBody SellerUpdateDTO dto,
	        BindingResult result) {

	    if (result.hasErrors()) {

	        String errorMessage = result.getFieldError().getDefaultMessage();

	        return ResponseEntity.badRequest()
	                .body(new ApiResponse(400, errorMessage, null));
	    }

	    ApiResponse response = sellerService.updateSellerProfile(email, dto);

	    return ResponseEntity.status(response.getCode()).body(response);
	}
	
	//PASSWORD CHANGE API
	@PutMapping("/change-password")
	public ResponseEntity<ApiResponse> changePassword(
	        @Valid @RequestBody ChangePasswordDTO requestDTO,
	        BindingResult result) {

	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest()
	                .body(new ApiResponse(400,
	                        result.getFieldError().getDefaultMessage(),
	                        null)); 
	    }

	    ApiResponse response = sellerService.changePassword(requestDTO);

	    return ResponseEntity.status(response.getCode()).body(response);
	}
		
}
