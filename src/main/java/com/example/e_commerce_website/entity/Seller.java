package com.example.e_commerce_website.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sellers")
@Data
public class Seller {
	
	@Id
	private String id;
	private String ownerName;
	private String shopName;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String pincode;
	private Boolean isVerified;
	private Boolean isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (isVerified == null)
            isVerified = false;

        if (isActive == null)
            isActive = true;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	

	
}

