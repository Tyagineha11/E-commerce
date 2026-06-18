package com.example.e_commerce_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
