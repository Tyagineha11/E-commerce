package com.example.e_commerce_website.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
	
	private String description;
		
	private Boolean isActive;
	
	private LocalDateTime createdAt;
	 @PrePersist
	    public void prePersist() {
	        this.createdAt = LocalDateTime.now();
	    }

}
	
