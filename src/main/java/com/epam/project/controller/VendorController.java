package com.epam.project.controller;

import com.epam.project.model.dto.VendorDto;
import com.epam.project.service.VendorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendors")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasRole('VENDOR')")
public class VendorController {
    private final VendorService vendorService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vendor:read')")
    public List<VendorDto> findAll() {
        return vendorService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vendor:read')")
    public VendorDto findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('vendor:create')")
    public VendorDto save(@RequestBody VendorDto vendorDto) {
        return vendorService.save(vendorDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('vendor:update')")
    public VendorDto update(@RequestBody VendorDto vendorDto) {
        return vendorService.update(vendorDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vendor:delete')")
    public void deleteById(@PathVariable Long id) {
        vendorService.deleteById(id);
    }
}
