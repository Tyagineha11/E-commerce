package com.example.e_commerce_website.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private Long userId;

    private Long addressId;

    private Double totalAmount;

    private Double shippingCharge;

    private Double finalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    /* PAYMENT DETAILS */
    private String paymentMethod;

    private String paymentId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL
    )
    private List<OrderItem> items;

    @PrePersist
    public void prePersist(){

        createdAt = LocalDateTime.now();

        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){

        updatedAt = LocalDateTime.now();
    }
}