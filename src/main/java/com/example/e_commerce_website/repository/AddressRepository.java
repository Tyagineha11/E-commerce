package com.example.e_commerce_website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.e_commerce_website.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
		List<Address> findByUserId(Long userId);

	    Optional<Address> findByIdAndUserId(Long id, Long userId);

	    List<Address> findByUserIdAndIsDefault(Long userId, Boolean isDefault);
}
