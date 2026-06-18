package com.example.e_commerce_website.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.WishlistResponseDTO;
import com.example.e_commerce_website.entity.Product;
import com.example.e_commerce_website.entity.Wishlist;
import com.example.e_commerce_website.repository.ProductRepository;
import com.example.e_commerce_website.repository.WishlistRepository;
import com.example.e_commerce_website.serviceInterface.WishlistService;

import jakarta.transaction.Transactional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    // =========================
    // ADD TO WISHLIST
    // =========================
    @Override
    public ApiResponse addToWishlist(Long userId, Long productId) {

        if (wishlistRepository.findByUserIdAndProductId(userId, productId).isPresent()) {
            return new ApiResponse(400, "Product already in wishlist", null);
        }

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return new ApiResponse(404, "Product not found", null);
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(productId);
        wishlist.setCreatedAt(LocalDateTime.now());

        wishlistRepository.save(wishlist);

        return new ApiResponse(200, "Added to wishlist", true);
    }

    // =========================
    // GET WISHLIST
    // =========================
    @Override
    public ApiResponse getWishlistByUser(Long userId) {

        List<com.example.e_commerce_website.entity.Wishlist> list =
                wishlistRepository.findByUserId(userId);

        List<WishlistResponseDTO> response = list.stream().map(w -> {

            WishlistResponseDTO dto = new WishlistResponseDTO();
            dto.setId(w.getId());
            dto.setProductId(w.getProductId());

            Product product = productRepository.findById(w.getProductId()).orElse(null);

            if (product != null) {

                // ✅ NAME
                dto.setProductName(product.getName());

                // ✅ PRICE (BigDecimal safe)
                dto.setPrice(product.getPrice() != null
                        ? product.getPrice()
                        : BigDecimal.ZERO);

                // ✅ IMAGE FROM ProductImage ENTITY (safe first image)
                String imageBase64 = null;

                if (product.getProductImages() != null
                        && !product.getProductImages().isEmpty()) {

                    imageBase64 = product.getProductImages()
                            .get(0)
                            .getImageBase64();
                }

                dto.setImageUrl(imageBase64);

            } else {
                dto.setProductName("Product not available");
                dto.setPrice(BigDecimal.ZERO);
                dto.setImageUrl(null);
            }

            return dto;

        }).collect(Collectors.toList());

        return new ApiResponse(200, "Wishlist fetched", response);
    }

    // =========================
    // REMOVE FROM WISHLIST
    // =========================
    @Override
    @Transactional
    public ApiResponse removeFromWishlist(Long userId, Long productId) {

        if (wishlistRepository.findByUserIdAndProductId(userId, productId).isEmpty()) {
            return new ApiResponse(404, "Product not in wishlist", null);
        }

        wishlistRepository.deleteByUserIdAndProductId(userId, productId);

        return new ApiResponse(200, "Removed from wishlist", true);
    }
}