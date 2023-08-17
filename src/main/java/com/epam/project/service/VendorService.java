package com.epam.project.service;

import com.epam.project.model.dto.VendorDto;

import java.util.List;
import java.util.UUID;

public interface VendorService {
    List<VendorDto> findAll();

    VendorDto findVendorById(Long id);

    VendorDto save(VendorDto vendorDto);

    VendorDto update(VendorDto vendorDto);

    void deleteVendorById(Long id);



}
