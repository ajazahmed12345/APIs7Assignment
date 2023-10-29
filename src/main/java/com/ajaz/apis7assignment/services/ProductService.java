package com.ajaz.apis7assignment.services;

import com.ajaz.apis7assignment.dtos.ProductDto;
import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto productDto) throws NotFoundException;

    Product getProductById(Long id) throws NotFoundException;

    List<Product> getAllProducts();

    List<Product> getProductsInCategory(String name) throws NotFoundException;

    boolean deleteProductById(Long id) throws NotFoundException;

    Product updateProductById(Long id, ProductDto productDto) throws NotFoundException;
}
