package com.example.e_commerce_website.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.e_commerce_website.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProduct_Id(Long productId);
}