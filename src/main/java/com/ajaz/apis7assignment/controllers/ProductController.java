package com.ajaz.apis7assignment.controllers;

import com.ajaz.apis7assignment.dtos.CategoryDto;
import com.ajaz.apis7assignment.dtos.ProductDto;
import com.ajaz.apis7assignment.exceptions.NotFoundException;
import com.ajaz.apis7assignment.models.Category;
import com.ajaz.apis7assignment.models.Product;
import com.ajaz.apis7assignment.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfproductserviceimpl") ProductService productService){
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws NotFoundException{

//        Product product = new Product();
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//
//        product.setCategory(category);
//        product.setDescription(productDto.getDescription());
//        product.setTitle(productDto.getTitle());
//        product.setPrice(productDto.getPrice());
//        product.setImage(productDto.getImage());

        ProductDto response = convertProductToProductDto(productService.createProduct(productDto));


        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        List<ProductDto> response = products.stream().map(e->convertProductToProductDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    public List<ProductDto> getProductsInCategory(@PathVariable("name") String name) throws NotFoundException{

        List<Product> products = productService.getProductsInCategory(name);

        List<ProductDto> response = products.stream().map(e->convertProductToProductDto(e)).collect(Collectors.toList());

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long id) throws NotFoundException{
        boolean ans = productService.deleteProductById(id);

//        if(ans == false){
//            throw new NotFoundException("Product you want to deleted does not exist.");
//        }

        return new ResponseEntity<>("Product with id = " +  id + " has been deleted.", HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws NotFoundException{
        Product product = productService.updateProductById(id, productDto);

        ProductDto response = convertProductToProductDto(product);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ProductDto convertProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());


        return productDto;
    }

    private CategoryDto convertCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;

    }

//    public List<ProductDto>


}
