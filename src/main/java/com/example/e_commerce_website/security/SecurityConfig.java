package com.example.e_commerce_website.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;
    
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http.exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint)); 
    	http
        .csrf(csrf -> csrf.disable())

        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(auth -> auth
            
            .requestMatchers(
            		"/",
                  "/index.html",
                  "/login.html",
                  "/header.html",
                  "/profile.html",
                  "/orders.html",
                  "/wishlist.html",
                  "/product.html",
                  "/product-detail.html",
                  "/cart.html",
                  "/address.html",
                  "/checkout.html",
                  "/seller-login.html",
                  "/seller-dashboard.html",
                  "/seller-header.html",
                  "/seller-sidebar.html",
                  "/seller-products.html",
                  "/seller-addProduct.html",
                  "/seller.updateProduct.html",
                  "/seller-profile.html",
                  "/change-password.html",
                  "/seller.orderProduct.html",
                  "/admin-user.html",
                  "/admin-login.html",
                  "/admin-dashboard.html",
                  "/admin-header.html",
                  "/admin-sidebar.html",
                  "/admin-category.html",
                  "/user-details.html",
                  "/admin-seller.html",
                  "/seller-details.html",
                  "/admin-products.html",
                  "/admin-common.js",
                  "/security.js",
                  "/auth.js",
                  "/seller-common.js",
                  "/category-products.html",
                  "/footer.html",
                  "/track.html",
                  "/css/**",
                  "/js/**",
                  "/images/**",
                  "/static/**",
                  "/favicon.ico",


                    "/api/users/login",
                    "/api/users/register",
                    "/api/sellers/login",
                    "/api/sellers/register",
                    "/api/admin/login",
                    "/api/admin/register",
                    "/api/auth/refresh",
                    
                    
                 // Public APIs
            	    "/api/products/getAll",
            	    "/api/products/getById/**",
            	    "/api/products/category/**",
            	    "/api/categories/getAll",
            	    "/api/product-images/product/**" 
                   
            )
            .permitAll()
           
            .requestMatchers("/api/admin/**")
            .hasRole("ADMIN")

            .requestMatchers("/api/sellers/**")
            .hasRole("SELLER")

            .requestMatchers(
            	    "/api/users/**",
            	    "/api/wishlist/**",
            	    "/api/cart/**",
            	    "/api/cart-items/**",
            	    "/api/addresses/**"
            	)
            .hasAnyRole("USER","ADMIN")
            
            .requestMatchers(
            		"/api/products/seller/**",
            		"/api/products/create/**",
            		"/api/product-images/upload/**",
            		"/api/products/update/**",
            		"/api/order-items/seller/**",
            	    "/api/order-items/status/**"
            		)
            .hasAnyRole("SELLER","ADMIN")
            
            .requestMatchers("/api/orders/**")
            .hasAnyRole("USER","ADMIN","SELLER")
            
         // =========================
            // ADMIN ONLY
            // =========================
            .requestMatchers(
            		"/api/admin/**",
            		"/api/categories/**"
            		)
            .hasRole("ADMIN")
            
        )
        
        

        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}