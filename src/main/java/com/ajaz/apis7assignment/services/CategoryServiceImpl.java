package com.ajaz.apis7assignment.services;

import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Category;
import com.ajaz.apis7assignment.repositories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategoryById(Long id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Ctaegory to get does not exist");
        }

        return categoryOptional.get();
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories;
    }
}
