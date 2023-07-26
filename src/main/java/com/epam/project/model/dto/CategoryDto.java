package com.epam.project.model.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private ProductDto product;
}
