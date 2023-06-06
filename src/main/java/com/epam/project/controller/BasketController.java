package com.epam.project.controller;

import com.epam.project.model.dto.BasketDto;
import com.epam.project.service.BasketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
@SecurityRequirement(name = "clothShopApi")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping("/all")
    public List<BasketDto> findAll() {
        return basketService.findAll();
    }

    @GetMapping("/{id}")
    public BasketDto findById(@PathVariable Long id) {
        return basketService.findById(id);
    }

    @PostMapping("/save")
    public BasketDto save(@RequestBody BasketDto basketDto) {
        return basketService.save(basketDto);
    }

    @PutMapping("/update")
    public BasketDto update(@RequestBody BasketDto basketDto) {
        return basketService.update(basketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        basketService.deleteById(id);
    }
}
