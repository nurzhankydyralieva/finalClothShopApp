package com.epam.project.controller;

import com.epam.project.model.dto.VendorDto;
import com.epam.project.service.VendorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@SecurityRequirement(name = "clothShopApi")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @GetMapping("/all")
    public List<VendorDto> findAll() {
        return vendorService.findAll();
    }

    @GetMapping("/{id}")
    public VendorDto findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @PostMapping("/save")
    public VendorDto save(@RequestBody VendorDto vendorDto) {
        return vendorService.save(vendorDto);
    }

    @PutMapping("/update")
    public VendorDto update(@RequestBody VendorDto vendorDto) {
        return vendorService.update(vendorDto);
    }

        @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        vendorService.deleteById(id);
    }

}
