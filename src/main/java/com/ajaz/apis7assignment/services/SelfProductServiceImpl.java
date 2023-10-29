package com.ajaz.apis7assignment.services;


import com.ajaz.apis7assignment.dtos.ProductDto;
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
    public Product createProduct(ProductDto productDto) throws NotFoundException{

        Product product = new Product();

        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());

        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategory());
        if(categoryOptional.isEmpty()){
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
        }
        else {
            Category category = categoryOptional.get();
            product.setCategory(category);
        }


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

    @Override
    public Product updateProductById(Long id, ProductDto productDto) throws NotFoundException {

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product you want to update does not exist.");
        }

        Product product = productOptional.get();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());

        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategory());

        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        }
        else{
            Category category = new Category();
            category.setName(productDto.getCategory());

            product.setCategory(category);
        }

        return productRepository.save(product);

    }
}
