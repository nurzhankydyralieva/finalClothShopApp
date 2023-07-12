package com.epam.project.model.dto;


import lombok.Data;

import java.util.Set;

@Data
public class VendorDto {
    private Long id;
    private String name;
    private ProductDto products;
}
