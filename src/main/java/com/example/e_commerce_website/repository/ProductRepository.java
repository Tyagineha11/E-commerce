package com.example.e_commerce_website.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.e_commerce_website.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findBySeller_Id(String sellerId);
}