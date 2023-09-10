package com.epam.project.controller;

import com.epam.project.model.dto.CategoryDto;
import com.epam.project.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasRole('BUYER')")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('buyer:read')")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:read')")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('buyer:create')")
    public CategoryDto save(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('buyer:update')")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:delete')")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
