package com.epam.project.controller;

import com.epam.project.model.dto.BasketDto;
import com.epam.project.service.BasketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/baskets")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasRole('BUYER')")
public class BasketController {
    private final BasketService basketService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('buyer:read')")
    public List<BasketDto> findAll() {
        return basketService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:read')")
    public BasketDto findById(@PathVariable Long id) {
        return basketService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('buyer:create')")
    public BasketDto save(@RequestBody BasketDto basketDto) {
        return basketService.save(basketDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('buyer:update')")
    public BasketDto update(@RequestBody BasketDto basketDto) {
        return basketService.update(basketDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:delete')")
    public void deleteById(@PathVariable Long id) {
        basketService.deleteById(id);
    }
}
