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
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application.properties")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Product product;
    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = Vendor.builder()
                .id(1L)
                .name("BMW")
                .build();
        product = Product.builder()
                .id(2L)
                .productName("Car")
                .price(55555)
                .quantity(1)
                .vendor(vendor).build();
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should delete product by vendor id")
    void itShouldDeleteByVendorId() {
        productRepository.deleteByVendorId(vendor.getId());
        Product productFromDb = productRepository.findProductById(product.getId());
        assertNull(productFromDb);
    }

    @Test
    @DisplayName("Test should find product by id")
    void itShouldFindProductById() {
        Product foundProductId = productRepository.findProductById(product.getId());

        assertThat(foundProductId).isNotNull();
    }
}