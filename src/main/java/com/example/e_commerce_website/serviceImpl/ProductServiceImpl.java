package com.example.e_commerce_website.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.*;
import com.example.e_commerce_website.entity.*;
import com.example.e_commerce_website.repository.*;
import com.example.e_commerce_website.serviceInterface.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // ================= CREATE =================
    @Override
    public ApiResponse createProduct(ProductRequestDTO dto) {

        Seller seller = sellerRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setShortDescription(dto.getShortDescription());
        product.setPrice(dto.getPrice());
        product.setDiscountPrice(dto.getDiscountPrice());
        product.setStock(dto.getStock());
        product.setBrand(dto.getBrand());
        product.setWeight(dto.getWeight());
        product.setColor(dto.getColor());
        product.setSize(dto.getSize());
        product.setStatus(dto.getStatus());

        product.setSeller(seller);
        product.setCategory(category);

        productRepository.save(product);

        return new ApiResponse(200, "Product created successfully", product);
    }

    // ================= GET ALL =================
    @Override
    public ApiResponse getAllProducts() {

        List<ProductResponseDTO> list = productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new ApiResponse(200, "Products fetched", list);
    }

    // ================= GET BY ID =================
    @Override
    public ApiResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ApiResponse(200, "Product found", mapToDTO(product));
    }

    // ================= UPDATE =================    
    @Override
    public ApiResponse updateProduct(Long id, ProductUpdateRequestDTO dto) {

        if(id == null){
            throw new RuntimeException("Product ID cannot be null");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Seller seller = sellerRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setShortDescription(dto.getShortDescription());

        product.setPrice(dto.getPrice());
        product.setDiscountPrice(dto.getDiscountPrice());

        product.setWeight(dto.getWeight());

        product.setStock(dto.getStock());
        product.setStatus(dto.getStatus());

        product.setBrand(dto.getBrand());
        product.setColor(dto.getColor());
        product.setSize(dto.getSize());

        product.setSeller(seller);
        product.setCategory(category);

        productRepository.save(product);

        return new ApiResponse(200, "Product updated successfully", product);
    }
    // ================= DELETE =================
    @Override
    public ApiResponse deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);

        return new ApiResponse(200, "Product deleted", null);
    }

    // ================= BY CATEGORY =================
    @Override
    public ApiResponse getProductsByCategoryId(Long categoryId) {

        List<ProductResponseDTO> list = productRepository
                .findByCategory_Id(categoryId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new ApiResponse(200, "Products by category", list);
    }

    // ================= BY SELLER =================
    @Override
    public ApiResponse getProductsBySellerId(String sellerId) {

        List<ProductResponseDTO> list = productRepository
                .findBySeller_Id(sellerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new ApiResponse(200, "Products by seller", list);
    }

    // ================= COMMON DTO =================
    private ProductResponseDTO mapToDTO(Product p) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setShortDescription(p.getShortDescription());
        dto.setPrice(p.getPrice());
        dto.setDiscountPrice(p.getDiscountPrice());
        dto.setStock(p.getStock());
        dto.setBrand(p.getBrand());
        dto.setColor(p.getColor());
        dto.setWeight(p.getWeight());
        dto.setSize(p.getSize());
        dto.setStatus(p.getStatus());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setSellerName(p.getSeller() != null ? p.getSeller().getOwnerName() : null);
        dto.setCategoryName(p.getCategory() != null ? p.getCategory().getName() : null);
        
        if (p.getProductImages() != null
                && !p.getProductImages().isEmpty()) {

            dto.setImageBase64(
                p.getProductImages()
                 .get(0)
                 .getImageBase64()
            );
        }

        return dto;
    }
}