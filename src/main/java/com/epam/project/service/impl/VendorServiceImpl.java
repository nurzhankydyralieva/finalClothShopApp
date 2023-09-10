package com.epam.project.service.impl;


import com.epam.project.exceptions.UserNotFoundException;
import com.epam.project.mapper.VendorMapper;
import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entity.Vendor;
import com.epam.project.repository.VendorRepository;
import com.epam.project.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDto> findAll() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<Vendor> orderedVendor = vendors.stream()
                .map(vendor -> new Vendor(
                        vendor.getId(),
                        vendor.getName(),
                        vendor.getProducts()
                ))
                .sorted()
                .collect(Collectors.toList());
        return vendorMapper.toDtos(orderedVendor);
    }

    @Override
    public VendorDto findVendorById(Long id) {
        Vendor vendor = vendorRepository.findVendorById(id);
        if (vendor != null) {
            VendorDto vendorDto = vendorMapper.toDto(vendor);
            return vendorDto;
        }
        throw new UserNotFoundException("Vendor with id " + id + " not available");
    }

    @Override
    public VendorDto save(VendorDto vendorDto) {
        Vendor vendor = vendorMapper.toEntity(vendorDto);
        vendor = vendorRepository.save(vendor);
        return vendorMapper.toDto(vendor);
    }

    @Override
    public VendorDto update(VendorDto vendorDto) {
        if (!vendorRepository.existsById(vendorDto.getId())) {
            throw new RuntimeException("Vendor not available");
        }
        Vendor vendor = vendorMapper.toEntity(vendorDto);
        vendor = vendorRepository.save(vendor);
        return vendorMapper.toDto(vendor);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

}



