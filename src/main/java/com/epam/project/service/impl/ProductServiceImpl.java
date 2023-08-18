package com.epam.project.service.impl;

import com.epam.project.mapper.ProductMapper;
import com.epam.project.mapper.VendorMapper;
import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Vendor;
import com.epam.project.repository.ProductRepository;
import com.epam.project.repository.VendorRepository;
import com.epam.project.service.ProductService;
import com.epam.project.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final VendorRepository vendorRepository;

    @Override
    public List<ProductDto> findAll() {
        return productMapper.toDtos(productRepository.findAll());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The product with id " + id + " not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        if (!productRepository.existsById(productDto.getId())) {
            throw new RuntimeException("Product not found");
        }
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }


//  Vendor может удалить только свои товары.
    @Override
    public void deleteByVendorId(Long id) {
        Vendor vendor = vendorRepository.findVendorById(id);
        if (vendor == null) {
            throw new NullPointerException("User with " + id + " not found in db");
        } else if (vendor.getId().equals(id)) {
            productRepository.deleteByVendorId(id);
            log.info("User with " + id + " deleted from db");
        }
    }
}
