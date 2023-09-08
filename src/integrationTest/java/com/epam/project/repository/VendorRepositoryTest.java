package com.epam.project.repository;

import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Vendor;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        vendorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all vendors")
    public void itShouldFindAllVendors() {
        //given
        List<Product> product = Arrays.asList(Product.builder().productName("Car").price(99999999).quantity(3).build());
        List<Product> product2 = Arrays.asList(Product.builder().productName("Airplane").price(100000000).quantity(5).build());
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        Vendor vendor2 = Vendor.builder().name("Delta Air Lines").build();
        vendor.setProducts(product);
        vendor2.setProducts(product2);
        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);
        //when
        List<Vendor> findAllVendor = vendorRepository.findAll();
        //then
        assertThat(findAllVendor).isNotNull();
        assertThat(findAllVendor.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test should save vendor to database")
    public void itShouldSaveVendor() {
        //given
        List<Product> product = Arrays.asList(Product.builder().productName("Car").price(99999999).quantity(3).build());
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        vendor.setProducts(product);
        vendorRepository.save(vendor);
        //when
        Vendor savedVendor = vendorRepository.findVendorById(vendor.getId());
        //then
        assertThat(savedVendor).isNotNull();
        assertThat(savedVendor.getName()).isEqualTo("Bugatti");
    }


    @Test
    @DisplayName("Test should find vendor by id")
    public void itShouldFindVendorById() {
        //given
        List<Product> product = Arrays.asList(Product.builder()
                .productName("Car")
                .price(99999999)
                .quantity(3)
                .build());
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        vendor.setProducts(product);
        vendorRepository.save(vendor);
        //when
        Vendor vendorId = vendorRepository.findVendorById(vendor.getId());
        //then
        assertThat(vendorId).isNotNull();
    }

    @Test
    @DisplayName("Test should update vendor")
    public void itShouldUpdateVendor() {
        //given
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        vendorRepository.save(vendor);
        Vendor vendorSave = vendorRepository.findVendorById(vendor.getId());
        vendorSave.setName("United Airlines");
        //when
        Vendor updatedVendor = vendorRepository.save(vendorSave);
        //then
        assertThat(updatedVendor.getName()).isNotNull();
    }

    @Test
    @DisplayName("Test should delete vendor")
    public void itShouldDeleteVendor() {
        //given
        Vendor vendor = Vendor.builder().name("Bugatti").build();
        vendorRepository.save(vendor);
        vendorRepository.deleteById(vendor.getId());
        //when
        Optional<Vendor> deletedVendor = vendorRepository.findById(vendor.getId());
        //then
        assertThat(deletedVendor).isEmpty();
    }
}