package com.example.e_commerce_website.serviceImpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.ProductImageResponseDTO;
import com.example.e_commerce_website.entity.Product;
import com.example.e_commerce_website.entity.ProductImage;
import com.example.e_commerce_website.repository.ProductImageRepository;
import com.example.e_commerce_website.repository.ProductRepository;
import com.example.e_commerce_website.serviceInterface.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    // ================= UPLOAD (BASE64) =================

    @Override
    public ApiResponse uploadImages(Long productId, MultipartFile[] files) {

        try {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            List<ProductImage> savedImages = new ArrayList<>();

            for (MultipartFile file : files) {

                ProductImage image = new ProductImage();

                image.setImageName(file.getOriginalFilename());

                // ✅ Convert to Base64
                String base64 = Base64.getEncoder().encodeToString(file.getBytes());

                image.setImageBase64(base64);
                image.setProduct(product);

                savedImages.add(image);
            }

            productImageRepository.saveAll(savedImages);

            return new ApiResponse(200, "Images uploaded as Base64 successfully", savedImages);

        } catch (Exception e) {
            return new ApiResponse(500, e.getMessage(), null);
        }
    }

    // ================= GET =================

    @Override
    public ApiResponse getImagesByProductId(Long productId) {

        List<ProductImageResponseDTO> list =
                productImageRepository.findByProduct_Id(productId)
                        .stream()
                        .map(this::mapToDTO)
                        .toList();

        return new ApiResponse(200, "Product images fetched", list);
    }

    // ================= DELETE =================

    @Override
    public ApiResponse deleteImage(Long imageId) {

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        productImageRepository.delete(image);

        return new ApiResponse(200, "Image deleted successfully", null);
    }
    
 // ================= UPDATE =================
    @Override
    public ApiResponse updateImage(Long imageId, MultipartFile file) {

        try {

            ProductImage image = productImageRepository.findById(imageId)
                    .orElseThrow(() -> new RuntimeException("Image not found"));

            // Update image name
            image.setImageName(file.getOriginalFilename());

            // Convert new image to Base64
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());

            image.setImageBase64(base64);

            // Save updated image
            productImageRepository.save(image);

            return new ApiResponse(200, "Image updated successfully", image);

        } catch (Exception e) {

            return new ApiResponse(500, e.getMessage(), null);
        }
    }

    // ================= DTO MAPPER =================

    private ProductImageResponseDTO mapToDTO(ProductImage image) {

        ProductImageResponseDTO dto = new ProductImageResponseDTO();

        dto.setId(image.getId());
        dto.setImageName(image.getImageName());
        dto.setProductId(image.getProduct().getId());

        dto.setImageBase64(image.getImageBase64());

        return dto;
    }
}