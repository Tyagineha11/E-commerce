package com.example.e_commerce_website.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* LONG DESCRIPTION */
    @Column(columnDefinition = "TEXT")
    private String description;

    /* SHORT DESCRIPTION */
    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    private BigDecimal price;

    private BigDecimal discountPrice;

    @Enumerated(EnumType.STRING)
    private Stock stock;

    private String brand;

    private Double weight;

    private String color;

    private String size;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    private List<ProductImage> productImages;

}