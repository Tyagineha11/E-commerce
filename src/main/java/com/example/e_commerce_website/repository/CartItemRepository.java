package com.example.e_commerce_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce_website.entity.CartItem;

@Repository
public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

}