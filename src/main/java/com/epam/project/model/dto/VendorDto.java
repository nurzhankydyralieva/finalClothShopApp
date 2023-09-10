package com.epam.project.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class VendorDto {
    private Long id;
    private String name;
    private List<ProductDto> products;
}
