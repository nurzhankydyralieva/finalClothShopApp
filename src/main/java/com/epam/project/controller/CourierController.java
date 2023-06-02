package com.epam.project.controller;

import com.epam.project.model.dto.CourierDto;
import com.epam.project.service.CourierService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@SecurityRequirement(name = "clothShopApi")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/all")
    public List<CourierDto> findAll() {
        return courierService.findAll();
    }

    @GetMapping("/{id}")
    public CourierDto findById(@PathVariable Long id) {
        return courierService.findById(id);
    }

    @PostMapping("/save")
    public CourierDto save(@RequestBody CourierDto courierDto) {
        return courierService.save(courierDto);
    }

    @PutMapping("/update")
    public CourierDto update(@RequestBody CourierDto courierDto) {
        return courierService.update(courierDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courierService.deleteById(id);
    }
}
