package com.epam.project.mapper;


import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtos(List<Product> product);

    List<Product> toEntities(List<ProductDto> productDtos);
}