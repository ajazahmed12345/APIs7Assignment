package com.ajaz.apis7assignment.services;


import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Category;
import com.ajaz.apis7assignment.models.Product;
import com.ajaz.apis7assignment.repositories.CategoryRepository;
import com.ajaz.apis7assignment.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfproductserviceimpl")
public class SelfProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product createProduct(Product product) throws NotFoundException{

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("category did not exist.");
        }

        Category category = categoryOptional.get();
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product to get is not present in the database.");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getProductsInCategory(String name) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);

        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category by this name does not exist.");
        }

        List<Product> products = productRepository.findByCategory_Name(name);


        return products;
    }

    @Override
    public boolean deleteProductById(Long id) throws NotFoundException{

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product you want to delete does not exist.");
        }

        productRepository.deleteById(id);

        return true;
    }
}
