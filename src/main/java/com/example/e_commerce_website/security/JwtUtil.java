package com.example.e_commerce_website.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration; 

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("type", "ACCESS") 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("type", "REFRESH")  // ⭐ ADD THIS
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public boolean isRefreshTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);

            String type = claims.get("type", String.class);

            return "REFRESH".equals(type);

        } catch (ExpiredJwtException e) {
            return false; // expired token
        } catch (Exception e) {
            return false; // invalid token
        }
    } 
    
    public String getTokenType(String token) {
        return extractClaims(token).get("type", String.class);
    }

    public Claims extractClaims(
            String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    } 

    public String extractEmail(
            String token) {

        return extractClaims(token)
                .getSubject();
    }

    public String extractRole(String token) {

        return extractClaims(token)
                .get("role", String.class);
    }
    
    public boolean validateToken(String token) {

        try {

            extractClaims(token);

            return true;

        }
        catch (Exception e) {

            return false;
        }
    }

}