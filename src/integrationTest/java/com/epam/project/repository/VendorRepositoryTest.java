package com.epam.project.repository;

import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Vendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class VendorRepositoryTest {
    @Autowired
    private VendorRepository vendorRepository;
    private List<Product> product;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        product = Arrays.asList(
                Product.builder()
                        .productName("Car")
                        .price(99999999)
                        .quantity(3)
                        .build(),
                Product.builder()
                        .productName("Car")
                        .price(99999999)
                        .quantity(3)
                        .build());
        vendor = Vendor.builder()
                .name("Bugatti")
                .build();
    }

    @AfterEach
    void tearDown() {
        vendorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all vendors")
    public void itShouldFindAllVendors() {
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        Vendor vendor2 = Vendor.builder().name("Delta Air Lines").build();
        vendor.setProducts(product);
        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);

        List<Vendor> findAllVendor = vendorRepository.findAll();

        assertThat(findAllVendor).isNotNull();
        assertThat(findAllVendor.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test should save vendor to database")
    public void itShouldSaveVendor() {
        vendor.setProducts(product);
        vendorRepository.save(vendor);

        Vendor savedVendor = vendorRepository.findVendorById(vendor.getId());

        assertThat(savedVendor).isNotNull();
        assertThat(savedVendor.getName()).isEqualTo("Bugatti");
    }


    @Test
    @DisplayName("Test should find vendor by id")
    public void itShouldFindVendorById() {
        vendor.setProducts(product);
        vendorRepository.save(vendor);

        Vendor vendorId = vendorRepository.findVendorById(vendor.getId());

        assertThat(vendorId).isNotNull();
    }

    @Test
    @DisplayName("Test should update vendor")
    public void itShouldUpdateVendor() {
        vendorRepository.save(vendor);
        Vendor vendorSave = vendorRepository.findVendorById(vendor.getId());
        vendorSave.setName("United Airlines");

        Vendor updatedVendor = vendorRepository.save(vendorSave);

        assertThat(updatedVendor.getName()).isNotNull();
    }

    @Test
    @DisplayName("Test should delete vendor")
    public void itShouldDeleteVendor() {
        vendorRepository.save(vendor);
        vendorRepository.deleteById(vendor.getId());

        Optional<Vendor> deletedVendor = vendorRepository.findById(vendor.getId());

        assertThat(deletedVendor).isEmpty();
    }
}