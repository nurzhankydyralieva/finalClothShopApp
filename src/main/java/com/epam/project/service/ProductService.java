package com.epam.project.service;

import com.epam.project.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    void deleteById(Long id);
}
