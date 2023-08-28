package com.epam.project.mapper;

import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entity.Vendor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class VendorMapperTest {

    @Test
    void toDto() {
        Vendor vendor = Vendor.builder()
                .id(1L)
                .name("Bugatti")
                .build();

        VendorMapper mapper = Mappers.getMapper(VendorMapper.class);
        assertNotNull(mapper);

        VendorDto vendorDto = mapper.toDto(vendor);
        assertNotNull(vendorDto);
        assertEquals(vendor.getId(), vendorDto.getId());
        assertEquals(vendor.getName(), vendorDto.getName());
        //  assertEquals(vendor.getProducts(), vendorDto.getProducts());
    }

    @Test
    void toEntity() {
        VendorDto vendorDto = VendorDto.builder()
                .id(2L)
                .name("BMW")
                .build();
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        assertNotNull(vendorMapper);

        Vendor vendor = vendorMapper.toEntity(vendorDto);
        assertNotNull(vendor);
        assertEquals(vendorDto.getId(), vendor.getId());
        assertEquals(vendorDto.getName(), vendor.getName());
    }

    @Test
    void toDtos() {
        Vendor vendor = Vendor.builder()
                .id(3L)
                .name("Chanel")
                .build();
        List<Vendor> vendors = Arrays.asList(vendor);
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        assertNotNull(vendorMapper);

        List<VendorDto> vendorDtos = vendorMapper.toDtos(vendors);
        assertNotNull(vendorDtos);
        assertEquals(vendorDtos.get(0).getId(), vendor.getId());
        assertEquals(vendorDtos.get(0).getName(), vendor.getName());
    }

    @Test
    void toEntities() {
        VendorDto vendorDto = VendorDto.builder()
                .id(3L)
                .name("Chanel")
                .build();
        List<VendorDto> vendorDtos = Arrays.asList(vendorDto);
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        assertNotNull(vendorMapper);

        List<Vendor> vendors = vendorMapper.toEntities(vendorDtos);
        assertNotNull(vendors);
        assertEquals(vendors.get(0).getId(), vendorDto.getId());
        assertEquals(vendors.get(0).getName(), vendorDto.getName());
    }
}