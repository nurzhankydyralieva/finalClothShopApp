package com.epam.project.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class VendorDto {
    private Long id;
    private String name;
    private List<ProductDto> products;
}
