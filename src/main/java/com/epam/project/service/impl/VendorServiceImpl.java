package com.epam.project.service.impl;


import com.epam.project.mapper.VendorMapper;
import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entity.User;
import com.epam.project.model.entity.Vendor;
import com.epam.project.repository.UserRepository;
import com.epam.project.repository.VendorRepository;
import com.epam.project.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorDto> findAll() {
        return vendorMapper.toDtos(vendorRepository.findAll());
    }

    @Override
    public VendorDto findById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor with id " + id + " not available"));
        return vendorMapper.toDto(vendor);
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
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }


}



