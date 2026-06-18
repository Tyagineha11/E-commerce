package com.example.e_commerce_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	boolean existsByName(String name);

}
