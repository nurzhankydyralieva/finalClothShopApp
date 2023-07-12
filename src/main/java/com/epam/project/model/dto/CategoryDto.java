package com.epam.project.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private ProductDto product;
}
