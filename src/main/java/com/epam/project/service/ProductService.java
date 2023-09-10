package com.epam.project.service;

import com.epam.project.model.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(ProductDto productDto);

  void deleteByVendorId(Long id);

}
