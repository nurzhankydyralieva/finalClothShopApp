package com.epam.project.mapper;

import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
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
class ProductMapperTest {

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        //given
        Product product = Product.builder()
                .id(1L)
                .productName("Car")
                .price(88888)
                .quantity(1)
                .build();
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        //when
        ProductDto productDto = productMapper.toDto(product);
        //then
        assertNotNull(productMapper);
        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getQuantity(), productDto.getQuantity());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        //given
        ProductDto productDto = ProductDto.builder()
                .id(2L)
                .productName("Airplane")
                .price(99999)
                .quantity(2)
                .build();
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        //when
        Product product = productMapper.toEntity(productDto);
        //then
        assertNotNull(productMapper);
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getProductName(), product.getProductName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getQuantity(), product.getQuantity());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        //given
        Product product = Product.builder()
                .id(3L)
                .productName("Train")
                .price(100000)
                .quantity(3)
                .build();
        List<Product> products = Arrays.asList(product);
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        //when
        List<ProductDto> productDtos = productMapper.toDtos(products);
        //then
        assertNotNull(productMapper);
        assertNotNull(productDtos);
        assertEquals(product.getId(), productDtos.get(0).getId());
        assertEquals(product.getProductName(), productDtos.get(0).getProductName());
        assertEquals(product.getPrice(), productDtos.get(0).getPrice());
        assertEquals(product.getQuantity(), productDtos.get(0).getQuantity());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        //given
        ProductDto productDto = ProductDto.builder()
                .id(3L)
                .productName("Train")
                .price(100000)
                .quantity(3)
                .build();
        List<ProductDto> productDtos = Arrays.asList(productDto);
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
        //when
        List<Product> products = productMapper.toEntities(productDtos);
        //then
        assertNotNull(productDtos);
        assertNotNull(productMapper);
        assertNotNull(products);
        assertEquals(products.get(0).getId(), productDto.getId());
        assertEquals(products.get(0).getProductName(), productDto.getProductName());
        assertEquals(products.get(0).getPrice(), productDto.getPrice());
        assertEquals(products.get(0).getQuantity(), productDto.getQuantity());

    }
}