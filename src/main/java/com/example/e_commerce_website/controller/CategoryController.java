package com.example.e_commerce_website.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CategoryRequestDTO;
import com.example.e_commerce_website.serviceInterface.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Create Category API
    @PostMapping("/create")
    public ApiResponse createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        return categoryService.createCategory(requestDTO);
    }
    
    //Get All API
    @GetMapping("/getAll")
    public ApiResponse getAllCategories() {
        return categoryService.getAllCategory();
    }
    
    // Get Category By ID  API
    @GetMapping("getById/{id}")
    public ApiResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    
    //Update By Id API
    @PutMapping("update/{id}")
    public ApiResponse updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO requestDTO) {

        return categoryService.updateCategory(id, requestDTO);
    }
    
    //Delete By Id API
    @DeleteMapping("delete/{id}")
    public ApiResponse deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}