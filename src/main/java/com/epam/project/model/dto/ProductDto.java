package com.epam.project.model.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private CategoryDto categoryId;
}

