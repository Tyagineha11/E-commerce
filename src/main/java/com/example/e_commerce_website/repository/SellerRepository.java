package com.example.e_commerce_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{
	
	 
	Optional<Seller> findByEmail(String email);
	
	Optional<Seller> findByPhone(String phone);
	
	Optional<Seller> findByShopName(String shopName);
	

}
