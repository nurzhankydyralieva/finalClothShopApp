package com.epam.project.controller;

import com.epam.project.model.dto.ProductDto;
import com.epam.project.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasAnyRole('BUYER', 'VENDOR')")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('buyer:read', 'vendor:read')")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('buyer:read', 'vendor:read')")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('buyer:create', 'vendor:create')")
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('buyer:update','vendor:update')")
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('buyer:delete','vendor:delete')")
    public void deleteById(@PathVariable Long id) {
//        productService.deleteById(id);
        productService.deleteByVendorId(id);
    }


}
