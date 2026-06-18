package com.example.e_commerce_website.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_images")
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

  //  @Lob
    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}