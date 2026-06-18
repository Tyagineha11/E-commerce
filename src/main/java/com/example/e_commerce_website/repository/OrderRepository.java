package com.example.e_commerce_website.repository;

import com.example.e_commerce_website.entity.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}