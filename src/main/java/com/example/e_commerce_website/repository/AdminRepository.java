package com.example.e_commerce_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByEmail(String email);

	boolean existsByPhone(String phone);
	boolean existsByemail(String email);

}
