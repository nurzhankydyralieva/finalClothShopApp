package com.epam.project.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class VendorDto {
    private Long id;
    private String name;
    private List<ProductDto> products;
}
