package com.epam.project.mapper;

import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductMapperTest {

    @Test
    void toDto() {
        Product product = Product.builder()
                .id(1L)
                .productName("Car")
                .price(88888)
                .quantity(1)
                .build();
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        assertNotNull(productMapper);

        ProductDto productDto = productMapper.toDto(product);
        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getQuantity(), productDto.getQuantity());
    }

    @Test
    void toEntity() {
        ProductDto productDto = ProductDto.builder()
                .id(2L)
                .productName("Airplane")
                .price(99999)
                .quantity(2)
                .build();
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        assertNotNull(productMapper);

        Product product = productMapper.toEntity(productDto);
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getProductName(), product.getProductName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getQuantity(), product.getQuantity());
    }

    @Test
    void toDtos() {
        Product product = Product.builder()
                .id(3L)
                .productName("Train")
                .price(100000)
                .quantity(3)
                .build();

        List<Product> products = Arrays.asList(product);
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        assertNotNull(productMapper);

        List<ProductDto> productDtos = productMapper.toDtos(products);
        assertNotNull(productDtos);
        assertEquals(product.getId(), productDtos.get(0).getId());
        assertEquals(product.getProductName(), productDtos.get(0).getProductName());
        assertEquals(product.getPrice(), productDtos.get(0).getPrice());
        assertEquals(product.getQuantity(), productDtos.get(0).getQuantity());
    }

    @Test
    void toEntities() {
        ProductDto productDto = ProductDto.builder()
                .id(3L)
                .productName("Train")
                .price(100000)
                .quantity(3)
                .build();
        List<ProductDto> productDtos = Arrays.asList(productDto);
        assertNotNull(productDtos);

        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        assertNotNull(productMapper);
        List<Product> products = productMapper.toEntities(productDtos);
        assertNotNull(products);
        assertEquals(products.get(0).getId(), productDto.getId());
        assertEquals(products.get(0).getProductName(), productDto.getProductName());
        assertEquals(products.get(0).getPrice(), productDto.getPrice());
        assertEquals(products.get(0).getQuantity(), productDto.getQuantity());

    }
}