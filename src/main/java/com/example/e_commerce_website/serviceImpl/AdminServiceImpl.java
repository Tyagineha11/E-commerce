package com.example.e_commerce_website.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.AdminRegisterDTO;
import com.example.e_commerce_website.dto.AdminloginDTO;
import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.entity.Admin;
import com.example.e_commerce_website.entity.Product;
import com.example.e_commerce_website.entity.Seller;
import com.example.e_commerce_website.entity.User;
import com.example.e_commerce_website.repository.AdminRepository;
import com.example.e_commerce_website.repository.ProductRepository;
import com.example.e_commerce_website.repository.SellerRepository;
import com.example.e_commerce_website.repository.UserRepository;
import com.example.e_commerce_website.security.JwtUtil;
import com.example.e_commerce_website.serviceInterface.AdminService;
import com.example.e_commerce_website.util.SHA256Util;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
		
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	//register
	@Override
	public ApiResponse adminRegister(AdminRegisterDTO dto) {
		
		if (adminRepository.existsByemail(dto.getEmail())) {
	        return new ApiResponse(400, "Email already registered", null);
	    }

	    // Check phone already exists
	    if (adminRepository.existsByPhone(dto.getPhone())) {
	        return new ApiResponse(400, "Phone already registered", null);
	    }

	    Admin admin = new Admin();
	    admin.setName(dto.getName());
	    admin.setEmail(dto.getEmail());
	    admin.setPassword(SHA256Util.encode(dto.getPassword()));
	    admin.setPhone(dto.getPhone());
	    admin.setCreatedAt(LocalDateTime.now());

	    adminRepository.save(admin);

	    return new ApiResponse(200, "Admin Registered Successfully", admin);
	}
	
	//login
	@Override
	public ApiResponse loginAdmin(AdminloginDTO requestDTO) {

	    Optional<Admin> optionalAdmin =
	            adminRepository.findByEmail(
	                    requestDTO.getEmail());

	    if (optionalAdmin.isEmpty()) {
	        return new ApiResponse(
	                400,
	                "Email not registered",
	                null);
	    }

	    Admin admin = optionalAdmin.get();

	    String encryptedPassword =
	            SHA256Util.encode(
	                    requestDTO.getPassword());

	    if (!admin.getPassword()
	            .equals(encryptedPassword)) {

	        return new ApiResponse(
	                400,
	                "Invalid Password",
	                null);
	    }

	    String accessToken =
	            jwtUtil.generateAccessToken(
	                    admin.getEmail(),
	                    "ADMIN"
	            );

	    String refreshToken =
	            jwtUtil.generateRefreshToken(
	                    admin.getEmail(),
	                    "ADMIN");

	    Map<String, Object> response =
	            new HashMap<>();

	    response.put(
	            "accessToken",
	            accessToken);

	    response.put(
	            "refreshToken",
	            refreshToken);

	    response.put(
	            "admin",
	            admin);

	    return new ApiResponse(
	            200,
	            "Admin Login Successfully",
	            response
	    );
	}
	
	@Override
	public ApiResponse getAllUsers() {

	    return new ApiResponse(
	            200,
	            "Users fetched successfully",
	            userRepository.findAll()
	    );
	}

	@Override
	public ApiResponse getAllSellers() {

	    return new ApiResponse(
	            200,
	            "Sellers fetched successfully",
	            sellerRepository.findAll()
	    );
	}
	
	@Override
	public ApiResponse getUserById(Long id) {

	    Optional<User> optionalUser = userRepository.findById(id);

	    if(optionalUser.isEmpty()) {
	        return new ApiResponse(404, "User Not Found", null);
	    }

	    return new ApiResponse(
	            200,
	            "User Found",
	            optionalUser.get()
	    );
	}
	
	@Override
	public ApiResponse getSellerById(String id) {

	    Optional<Seller> seller =
	            sellerRepository.findById(id);

	    if(seller.isEmpty()) {
	        return new ApiResponse(
	                404,
	                "Seller Not Found",
	                null);
	    }

	    return new ApiResponse(
	            200,
	            "Seller Found",
	            seller.get());
	}
	
	@Override
	public ApiResponse getProductById(Long id) {

	    Optional<Product> product =
	            productRepository.findById(id);

	    if(product.isEmpty()){
	        return new ApiResponse(
	                404,
	                "Product Not Found",
	                null
	        );
	    }

	    return new ApiResponse(
	            200,
	            "Product Found",
	            product.get()
	    );
	}
	

}
