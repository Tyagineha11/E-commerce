package com.example.e_commerce_website.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ChangePasswordDTO;
import com.example.e_commerce_website.dto.SellerLoginDTO;
import com.example.e_commerce_website.dto.SellerRegisterDTO;
import com.example.e_commerce_website.dto.SellerUpdateDTO;
import com.example.e_commerce_website.entity.Seller;
import com.example.e_commerce_website.repository.SellerRepository;
import com.example.e_commerce_website.security.JwtUtil;
import com.example.e_commerce_website.serviceInterface.SellerService;
import com.example.e_commerce_website.util.SHA256Util;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	private SellerRepository sellerRepository;
	
	 @Autowired
	    private JwtUtil jwtUtil;
	

	
	//REGISTER SELLER 
	@Override
	public ApiResponse registerSeller(SellerRegisterDTO registerDTO) {
		
		if(sellerRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
			return new ApiResponse(400,"Emial Already Exists", null);
		}
		
		if(sellerRepository.findByShopName(registerDTO.getShopName()).isPresent()) {
			return new ApiResponse(400,"Shop Name Already Exits", null);
		}
		
		if(sellerRepository.findByPhone(registerDTO.getPhone()).isPresent()) {
			return new ApiResponse(400,"Phone Already Exits", null);
		}
		
		long count = sellerRepository.count() + 1;
		String sellerId = String.format("S%03d", count);
		
		Seller seller = new Seller();
		
		seller.setId(sellerId);
		seller.setShopName(registerDTO.getShopName());
		seller.setOwnerName(registerDTO.getOwnerName());
		seller.setEmail(registerDTO.getEmail());
		seller.setPassword(SHA256Util.encode(registerDTO.getPassword()));
		seller.setPhone(registerDTO.getPhone());
        seller.setAddress(registerDTO.getAddress());
        seller.setPincode(registerDTO.getPincode());
        
        sellerRepository.save(seller);
        
        return new ApiResponse(200, "Seller Registered Successfully", seller);
	}
	
	//LOGIN SELLER
	@Override
	public ApiResponse loginSeller(SellerLoginDTO registerDTO) {

	    Seller seller = sellerRepository.findByEmail(
	            registerDTO.getEmail())
	            .orElse(null);

	    if (seller == null) {
	        return new ApiResponse(
	                404,
	                "Seller not Found",
	                null);
	    }

	    String encodePassword =
	            SHA256Util.encode(
	                    registerDTO.getPassword());

	    if (!seller.getPassword()
	            .equals(encodePassword)) {

	        return new ApiResponse(
	                401,
	                "Invalid Password",
	                null);
	    }

	    if (Boolean.FALSE.equals(
	            seller.getIsActive())) {

	        return new ApiResponse(
	                403,
	                "Seller account is inactive",
	                null);
	    }

	    String accessToken =
	            jwtUtil.generateAccessToken(
	                    seller.getEmail(),
	                    "SELLER"
	            );

	    String refreshToken =
	            jwtUtil.generateRefreshToken(
	                    seller.getEmail(),
	                    "SELLER");

	    Map<String, Object> response =
	            new HashMap<>();

	    response.put(
	            "accessToken",
	            accessToken);

	    response.put(
	            "refreshToken",
	            refreshToken);

	    response.put(
	            "seller",
	            seller);

	    return new ApiResponse(
	            200,
	            "Login successful",
	            response);
	}
	
	//GET ALL SELLER
	@Override
	public ApiResponse getAllSellers() {

	    List<Seller> sellers = sellerRepository.findAll();

	    if (sellers.isEmpty()) {
	        return new ApiResponse(404, "No sellers found", Collections.emptyList());
	    }

	    return new ApiResponse(200, "All sellers fetched", sellers);
	}
	
	//GET SELLER BY EMAIL
	@Override
	public ApiResponse getSellerProfile(String email) {

	    List<Seller> sellerList = sellerRepository.findByEmail(email)
	            .map(List::of)
	            .orElse(Collections.emptyList());

	    if (sellerList.isEmpty()) {
	        return new ApiResponse(404, "Seller not found", sellerList);
	    }

	    return new ApiResponse(200, "Seller profile fetched successfully", sellerList);
	}
	
	//UPDATE SELLER 
	@Override
	public ApiResponse updateSellerProfile(String email, SellerUpdateDTO dto) {

	    Seller seller = sellerRepository.findByEmail(email)
	            .orElse(null);

	    if (seller == null) {
	        return new ApiResponse(404, "Seller not found", null);
	    }

	    // Update fields
	    seller.setOwnerName(dto.getOwnerName());
	    seller.setShopName(dto.getShopName());
	    seller.setPhone(dto.getPhone());
	    seller.setAddress(dto.getAddress());
	    seller.setPincode(dto.getPincode());

	    sellerRepository.save(seller);

	    return new ApiResponse(200, "Seller profile updated successfully", seller);
	}
	
	//change password
	
	@Override
	public ApiResponse changePassword(ChangePasswordDTO dto) {

	    Seller seller = sellerRepository.findByEmail(dto.getEmail())
	            .orElse(null);

	    if (seller == null) {
	        return new ApiResponse(404, "Seller not found", null);
	    }

	    String oldEncrypted = SHA256Util.encode(dto.getOldPassword());

	    if (!seller.getPassword().equals(oldEncrypted)) {
	        return new ApiResponse(401, "Old password is incorrect", null);
	    }

	    seller.setPassword(SHA256Util.encode(dto.getNewPassword()));

	    sellerRepository.save(seller);

	    return new ApiResponse(200, "Password changed successfully", null);
	}
	
}
