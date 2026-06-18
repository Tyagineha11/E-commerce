package com.example.e_commerce_website.dto;

import lombok.Data;

@Data
public class RefreshTokenRequestDTO {
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}