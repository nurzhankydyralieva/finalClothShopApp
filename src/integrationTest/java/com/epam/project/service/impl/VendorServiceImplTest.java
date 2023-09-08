package com.epam.project.service.impl;

import com.epam.project.mapper.VendorMapper;
import com.epam.project.model.dto.VendorDto;
import com.epam.project.model.entity.Vendor;
import com.epam.project.repository.VendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
class VendorServiceImplTest {
    @InjectMocks
    private VendorServiceImpl vendorService;
    @Mock
    private VendorRepository vendorRepository;
    @Mock
    private VendorMapper vendorMapper;

    @BeforeEach
    void setUp() {
        vendorRepository = mock(VendorRepository.class);
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        vendorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all vendors")
    void findAll() {
        Long vendorId = 1L;
        List<VendorDto> vendorDtoList = Arrays.asList(
                VendorDto.builder()
                        .id(vendorId)
                        .name("Bugatti")
                        .build());
        List<Vendor> vendorList = Arrays.asList(
                Vendor.builder()
                        .id(vendorId)
                        .name("Bugatti")
                        .build());
        when(vendorRepository.findAll()).thenReturn(vendorList);
        when(vendorMapper.toDtos(vendorList)).thenReturn(vendorDtoList);
        //when
        List<VendorDto> allVendors = vendorService.findAll();
        //then
        assertNotNull(allVendors);
        verify(vendorMapper, times(1)).toDtos(vendorList);
        verify(vendorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find vendor by Id")
    void findVendorById() {
        //given
        Long vendorId = 2L;
        VendorDto vendorDto = VendorDto.builder()
                .id(vendorId)
                .name("Rolls-Royce")
                .build();
        Vendor vendor = Vendor.builder()
                .id(vendorId)
                .name("Rolls-Royce")
                .build();
        when(vendorRepository.findVendorById(vendorId)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);
        //when
        VendorDto foundVendorId = vendorService.findVendorById(vendor.getId());
        //then
        assertNotNull(foundVendorId);
        assertEquals(vendorId, foundVendorId.getId());
        verify(vendorMapper, times(1)).toDto(vendor);
        verify(vendorRepository, times(1)).findVendorById(vendor.getId());
    }

    @Test
    @DisplayName("Test should save new vendor to database")
    void itShouldSaveVendor() {
        //given
        VendorDto vendorDto = VendorDto.builder()
                .name("Cadillac")
                .build();
        Vendor vendor = Vendor.builder()
                .id(3L)
                .name("Cadillac")
                .build();
        when(vendorMapper.toEntity(vendorDto)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        //when
        VendorDto savedVendor = vendorService.save(vendorDto);
        //then
        assertNotNull(savedVendor);
        assertEquals(vendorDto, savedVendor);
        verify(vendorMapper, times(1)).toEntity(vendorDto);
        verify(vendorMapper, times(1)).toDto(vendor);
        verify(vendorRepository, times(1)).save(vendor);
    }

    @Test
    @DisplayName("Test should update vendor")
    void update() {
        //given
        VendorDto vendorDto = VendorDto.builder()
                .name("Cadillac")
                .build();
        Vendor vendor = Vendor.builder()
                .id(4L)
                .name("Cadillac")
                .build();
        when(vendorMapper.toEntity(vendorDto)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        vendorDto.setName("Cadillac Luxury Vehicles");
        //when
        VendorDto updatedVendor = vendorService.save(vendorDto);
        //then
        assertNotNull(updatedVendor);
        assertEquals(vendorDto, updatedVendor);
        assertEquals("Cadillac Luxury Vehicles", updatedVendor.getName());
    }

    @Test
    @DisplayName("Test should delete vendor by id")
    void deleteVendorById() {
        Vendor vendor = Vendor.builder()
                .id(4L)
                .name("Cadillac")
                .build();
        vendorRepository.save(vendor);
        vendorRepository.deleteById(vendor.getId());
        //when
        Vendor deletedVendor = vendorRepository.findVendorById(vendor.getId());
        //then
        assertThat(deletedVendor).isNull();
    }
}