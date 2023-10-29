package com.ajaz.apis7assignment.controllers;

import com.ajaz.apis7assignment.dtos.CategoryDto;
import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Category;
import com.ajaz.apis7assignment.services.CategoryService;
import com.ajaz.apis7assignment.services.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) throws NotFoundException {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = convertCategoryToCategoryDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    private CategoryDto convertCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;

    }

    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();

        List<CategoryDto> response = categories.stream().map(e-> convertCategoryToCategoryDto(e)).collect(Collectors.toList());

        return response;

    }


}
