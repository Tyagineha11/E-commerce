package com.example.e_commerce_website.repository;

import com.example.e_commerce_website.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findByOrderId(Long orderId);

    List<OrderItem> findBySellerId(String sellerId);
}