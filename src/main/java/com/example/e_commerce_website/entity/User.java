package com.example.e_commerce_website.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "userss")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private LocalDate dateOfBirth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
}
