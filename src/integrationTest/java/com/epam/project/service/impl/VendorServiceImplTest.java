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
    private Long vendorId = 1L;
    private List<VendorDto> vendorDtoList;
    private List<Vendor> vendorList;
    private VendorDto vendorDto;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendorRepository = mock(VendorRepository.class);
        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
        vendorDtoList = Arrays.asList(VendorDto.builder()
                .id(vendorId)
                .name("Bugatti")
                .build());
        vendorList = Arrays.asList(Vendor.builder()
                .id(vendorId)
                .name("Bugatti")
                .build());
        vendorDto = VendorDto.builder()
                .id(vendorId)
                .name("Rolls-Royce")
                .build();
        vendor = Vendor.builder()
                .id(vendorId)
                .name("Rolls-Royce")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        vendorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all vendors")
    void findAll() {
        when(vendorRepository.findAll()).thenReturn(vendorList);
        when(vendorMapper.toDtos(vendorList)).thenReturn(vendorDtoList);

        List<VendorDto> allVendors = vendorService.findAll();

        assertNotNull(allVendors);
        verify(vendorMapper, times(1)).toDtos(vendorList);
        verify(vendorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find vendor by Id")
    void findVendorById() {
        when(vendorRepository.findVendorById(vendorId)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);

        VendorDto foundVendorId = vendorService.findVendorById(vendor.getId());

        assertNotNull(foundVendorId);
        assertEquals(vendorId, foundVendorId.getId());
        verify(vendorMapper, times(1)).toDto(vendor);
        verify(vendorRepository, times(1)).findVendorById(vendor.getId());
    }

    @Test
    @DisplayName("Test should save new vendor to database")
    void itShouldSaveVendor() {
        when(vendorMapper.toEntity(vendorDto)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        VendorDto savedVendor = vendorService.save(vendorDto);

        assertNotNull(savedVendor);
        assertEquals(vendorDto, savedVendor);
        verify(vendorMapper, times(1)).toEntity(vendorDto);
        verify(vendorMapper, times(1)).toDto(vendor);
        verify(vendorRepository, times(1)).save(vendor);
    }

    @Test
    @DisplayName("Test should update vendor")
    void update() {

        when(vendorMapper.toEntity(vendorDto)).thenReturn(vendor);
        when(vendorMapper.toDto(vendor)).thenReturn(vendorDto);
        when(vendorRepository.save(vendor)).thenReturn(vendor);
        vendorDto.setName("Cadillac Luxury Vehicles");

        VendorDto updatedVendor = vendorService.save(vendorDto);

        assertNotNull(updatedVendor);
        assertEquals(vendorDto, updatedVendor);
        assertEquals("Cadillac Luxury Vehicles", updatedVendor.getName());
    }

    @Test
    @DisplayName("Test should delete vendor by id")
    void deleteVendorById() {
        vendorRepository.save(vendor);
        vendorRepository.deleteById(vendor.getId());

        Vendor deletedVendor = vendorRepository.findVendorById(vendor.getId());

        assertThat(deletedVendor).isNull();
    }
}