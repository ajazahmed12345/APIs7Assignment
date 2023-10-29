package com.ajaz.apis7assignment.repositories;


import com.ajaz.apis7assignment.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Name(String name);
}
