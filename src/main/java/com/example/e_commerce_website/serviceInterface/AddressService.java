package com.example.e_commerce_website.serviceInterface;

import com.example.e_commerce_website.dto.AddressRequestDTO;
import com.example.e_commerce_website.dto.ApiResponse;

public interface AddressService {
	
	ApiResponse addAddress(Long userId, AddressRequestDTO dto);

    ApiResponse getAllAddresses(Long userId);

    ApiResponse updateAddress(Long userId, Long addressId, AddressRequestDTO dto);

    ApiResponse deleteAddress(Long userId, Long addressId);

    ApiResponse setDefaultAddress(Long userId, Long addressId);

}
