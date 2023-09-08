package com.epam.project.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Set<CategoryDto> categories;
    private Set<OrderDto> orders;
    private VendorDto vendor;
    private UserDto user;
}

