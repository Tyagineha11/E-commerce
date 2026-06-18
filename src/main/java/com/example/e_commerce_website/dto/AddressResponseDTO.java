package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private Long id;

    private String fullName;

    private String phone;

    private String addressLine1;

    private String addressLine2;

    private String landmark;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private Boolean isDefault;
}