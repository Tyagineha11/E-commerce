package com.example.e_commerce_website.security;

import com.example.e_commerce_website.exception.BadTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
    		throws ServletException, IOException { 

        String authHeader = request.getHeader("Authorization");

        // ✅ CASE 1: TOKEN MISSING
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { 
            request.setAttribute("exception", "Missing Token");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); 

        try {

            if (!jwtUtil.validateToken(token)) {
                throw new BadTokenException("Invalid Token");
            }

            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);

        }
        catch (ExpiredJwtException e) {
            throw new BadTokenException("Token Expired");
        } 
        catch (MalformedJwtException e) {
            throw new BadTokenException("Invalid Token");
        }
        catch (Exception e) {
            throw new BadTokenException("Invalid Token");
        }
    }
}