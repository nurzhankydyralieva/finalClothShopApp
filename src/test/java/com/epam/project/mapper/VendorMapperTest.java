package com.epam.project.mapper;

import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Vendor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class VendorMapperTest {
    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        //given
        List<Product> product = Arrays.asList(Product.builder()
                .id(1L)
                .productName("Car")
                .price(99999999)
                .quantity(3)
                .build());
        Vendor vendor = Vendor.builder()
                .id(1L)
                .name("Bugatti")
                .build();
        vendor.setProducts(product);
        VendorMapper mapper = Mappers.getMapper(VendorMapper.class);
        //when
        VendorDto vendorDto = mapper.toDto(vendor);
        //then

        assertNotNull(vendorDto);
        assertEquals(vendor.getId(), vendorDto.getId());
        assertEquals(vendor.getName(), vendorDto.getName());
        assertEquals(vendor.getProducts().get(0).getId(), vendorDto.getProducts().get(0).getId());
        assertEquals(vendor.getProducts().get(0).getProductName(), vendorDto.getProducts().get(0).getProductName());
        assertEquals(vendor.getProducts().get(0).getPrice(), vendorDto.getProducts().get(0).getPrice());
        assertEquals(vendor.getProducts().get(0).getQuantity(), vendorDto.getProducts().get(0).getQuantity());

    }


    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        //given
        VendorDto vendorDto = VendorDto.builder()
                .id(2L)
                .name("BMW")
                .build();
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        //when
        Vendor vendor = vendorMapper.toEntity(vendorDto);
        //then
        assertNotNull(vendorMapper);
        assertNotNull(vendor);
        assertEquals(vendorDto.getId(), vendor.getId());
        assertEquals(vendorDto.getName(), vendor.getName());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        //given
        Vendor vendor = Vendor.builder()
                .id(3L)
                .name("Chanel")
                .build();
        List<Vendor> vendors = Arrays.asList(vendor);
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        //when
        List<VendorDto> vendorDtos = vendorMapper.toDtos(vendors);
        //then
        assertNotNull(vendorMapper);
        assertNotNull(vendorDtos);
        assertEquals(vendorDtos.get(0).getId(), vendor.getId());
        assertEquals(vendorDtos.get(0).getName(), vendor.getName());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        //given
        VendorDto vendorDto = VendorDto.builder()
                .id(3L)
                .name("Chanel")
                .build();
        List<VendorDto> vendorDtos = Arrays.asList(vendorDto);
        VendorMapper vendorMapper = Mappers.getMapper(VendorMapper.class);
        //when
        List<Vendor> vendors = vendorMapper.toEntities(vendorDtos);
        //then
        assertNotNull(vendorMapper);
        assertNotNull(vendors);
        assertEquals(vendors.get(0).getId(), vendorDto.getId());
        assertEquals(vendors.get(0).getName(), vendorDto.getName());
    }
}