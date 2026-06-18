package com.example.e_commerce_website.controller;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.RefreshTokenRequestDTO;
import com.example.e_commerce_website.security.JwtUtil;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/refresh")
    public ApiResponse refreshToken(@RequestBody RefreshTokenRequestDTO dto) {

        String refreshToken = dto.getRefreshToken();

        if (!jwtUtil.isRefreshTokenValid(refreshToken)) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid Refresh Token", null);
        }

        Claims claims = jwtUtil.extractClaims(refreshToken);

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        String newAccessToken = jwtUtil.generateAccessToken(email, role);

        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", newAccessToken);

        return new ApiResponse(200, "Access Token Refreshed", map);
    }
}