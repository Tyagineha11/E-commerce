package com.example.e_commerce_website.serviceInterface;

import org.springframework.web.multipart.MultipartFile;
import com.example.e_commerce_website.dto.ApiResponse;

public interface ProductImageService {

    ApiResponse uploadImages(Long productId, MultipartFile[] files);

    ApiResponse getImagesByProductId(Long productId);

    ApiResponse deleteImage(Long imageId);
    
    ApiResponse updateImage(Long imageId, MultipartFile file);
}