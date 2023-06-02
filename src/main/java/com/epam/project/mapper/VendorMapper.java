package com.epam.project.mapper;


import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entitity.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorDto toDto(Vendor vendor);

    Vendor toEntity(VendorDto vendorDto);

    List<VendorDto> toDtos(List<Vendor> vendors);

    List<Vendor> toEntities(List<VendorDto> vendorDtos);
}
