package com.epam.project.controller;

import com.epam.project.model.dto.CourierDto;
import com.epam.project.service.CourierService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/couriers")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasRole('BUYER')")
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('buyer:read')")
    public List<CourierDto> findAll() {
        return courierService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:read')")
    public CourierDto findById(@PathVariable Long id) {
        return courierService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('buyer:create')")
    public CourierDto save(@RequestBody CourierDto courierDto) {
        return courierService.save(courierDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('buyer:update')")
    public CourierDto update(@RequestBody CourierDto courierDto) {
        return courierService.update(courierDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:delete')")
    public void deleteById(@PathVariable Long id) {
        courierService.deleteById(id);
    }
}
