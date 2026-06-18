package com.example.e_commerce_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByPhone(String phone);

}
