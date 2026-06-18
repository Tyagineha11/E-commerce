package com.example.e_commerce_website.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CategoryRequestDTO;
import com.example.e_commerce_website.dto.CategoryResponseDTO;
import com.example.e_commerce_website.entity.Category;
import com.example.e_commerce_website.repository.CategoryRepository;
import com.example.e_commerce_website.serviceInterface.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//Create Category
	@Override
	public ApiResponse createCategory(CategoryRequestDTO requestDTO) {

	    if (categoryRepository.existsByName(requestDTO.getName())) {
	        return new ApiResponse(400, "Category already exists", null);
	    }

	    Category category = new Category();
	    category.setName(requestDTO.getName());
	    category.setDescription(requestDTO.getDescription());
	    category.setIsActive(true);
	    category.setCreatedAt(LocalDateTime.now());

	    categoryRepository.save(category);

	    return new ApiResponse(200, "Category Created Successfully", true);
	}
	
	//Get All Category
	@Override
	public ApiResponse getAllCategory() {
		List<Category> categories = categoryRepository.findAll();

	    List<CategoryResponseDTO> responseList = categories.stream().map(category -> {
	        CategoryResponseDTO dto = new CategoryResponseDTO();
	        dto.setId(category.getId());
	        dto.setName(category.getName()); 
	        dto.setDescription(category.getDescription());
	        dto.setIsActive(category.getIsActive());
	        return dto;
	    }).collect(Collectors.toList());

	    return new ApiResponse(200, "Category List", responseList);
	}
	
	//Get Category By Id
	@Override
	public ApiResponse getCategoryById(Long id) {

	    Optional<Category> optionalCategory = categoryRepository.findById(id);

	    if (!optionalCategory.isPresent()) {
	        return new ApiResponse(404, "Category not found", null);
	    }

	    Category category = optionalCategory.get();

	    CategoryResponseDTO dto = new CategoryResponseDTO();
	    dto.setId(category.getId());
	    dto.setName(category.getName());
	    dto.setDescription(category.getDescription());
	    dto.setIsActive(category.getIsActive());

	    return new ApiResponse(200, "Category found", dto);
	}
	
	//Update by Id
	 @Override
	    public ApiResponse updateCategory(Long id, CategoryRequestDTO requestDTO) {

	        Optional<Category> optional = categoryRepository.findById(id);

	        if (!optional.isPresent()) {
	            return new ApiResponse(404, "Category not found", null);
	        }

	        Category category = optional.get();

	        // Duplicate name check (important)
	        if (!category.getName().equals(requestDTO.getName())
	                && categoryRepository.existsByName(requestDTO.getName())) {
	            return new ApiResponse(400, "Category name already exists", null);
	        }

	        category.setName(requestDTO.getName());
	        category.setDescription(requestDTO.getDescription());

	        categoryRepository.save(category);

	        return new ApiResponse(200, "Category updated successfully", category);
	    }
	 
	 
	 //Delete By Id
	 @Override
	 public ApiResponse deleteCategory(Long id) {

	     Optional<Category> optional = categoryRepository.findById(id);

	     if (!optional.isPresent()) {
	         return new ApiResponse(404, "Category not found", null);
	     }

	     categoryRepository.deleteById(id);

	     return new ApiResponse(200, "Category deleted successfully", true);
	 }
	
	
	
	
}
