package com.example.e_commerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double totalPrice;

    // MANY CART ITEMS -> ONE CART
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    // MANY CART ITEMS -> ONE PRODUCT
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}