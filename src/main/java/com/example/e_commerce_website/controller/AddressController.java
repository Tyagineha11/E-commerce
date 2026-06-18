package com.example.e_commerce_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.AddressRequestDTO;
import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.serviceImpl.AddressServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    
    // Add Address API
    @PostMapping("/add")
    public ApiResponse addAddress(@RequestParam Long userId,@Valid @RequestBody AddressRequestDTO dto) 
    {
        return addressService.addAddress(userId, dto);
    }

    
   
    // Get All Addresses API
    @GetMapping("/get")
    public ApiResponse getAllAddresses(@RequestParam Long userId) 
    {
        return addressService.getAllAddresses(userId);
    }

    
   
    // Update Address API
    @PutMapping("/update/{id}")
    public ApiResponse updateAddress( @RequestParam Long userId, @PathVariable Long id,@Valid @RequestBody AddressRequestDTO dto) 
    {
        return addressService.updateAddress(userId, id, dto);
    }

    
   
    // Delete Address API
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteAddress(@RequestParam Long userId, @PathVariable Long id) {
        return addressService.deleteAddress(userId, id);
    }

    
   
    // Set Default Address API
    @PutMapping("/default/{id}")
    public ApiResponse setDefaultAddress(@RequestParam Long userId, @PathVariable Long id) 
    {
        return addressService.setDefaultAddress(userId, id);
    }
 }
