package com.epam.project.model.dto;

import com.epam.project.model.entity.Category;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Set<CategoryDto> categories;
    private Set<VendorDto> vendor;
    private Set<OrderDto> orders;
    private UserDto user;
}

