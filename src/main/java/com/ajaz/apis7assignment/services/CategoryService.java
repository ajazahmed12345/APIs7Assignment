package com.ajaz.apis7assignment.services;

import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {


    Category getCategoryById(Long id) throws NotFoundException;

    List<Category> getAllCategories();
}
