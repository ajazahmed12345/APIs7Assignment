package com.ajaz.apis7assignment.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private double price;
    private String category;
}
