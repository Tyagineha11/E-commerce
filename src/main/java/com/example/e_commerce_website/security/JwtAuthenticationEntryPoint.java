package com.example.e_commerce_website.security;

import com.example.e_commerce_website.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException 
    {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String message = "Unauthorized";

        Object missing = request.getAttribute("exception");

        // ✅ Missing token case
        if (missing != null && missing.equals("Missing Token")) {
            message = "Missing Token";
        }

        ApiResponse apiResponse = new ApiResponse(
        		HttpStatus.UNAUTHORIZED.value(),
                message,
                null
        );

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(apiResponse));
    }
}