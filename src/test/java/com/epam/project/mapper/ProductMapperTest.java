package com.epam.project.mapper;

import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
import org.junit.jupiter.api.BeforeEach;
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

    private ProductMapper mapper;
    private Product product;
    private ProductDto productDto;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(ProductMapper.class);
        product = Product.builder().id(1L).productName("Car").price(88888).quantity(1).build();
        productDto = ProductDto.builder().id(2L).productName("Airplane").price(99999).quantity(2).build();
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        ProductDto productDto = mapper.toDto(product);

        assertNotNull(mapper);
        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getQuantity(), productDto.getQuantity());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        Product product = mapper.toEntity(productDto);

        assertNotNull(mapper);
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getProductName(), product.getProductName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getQuantity(), product.getQuantity());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        List<Product> products = Arrays.asList(product);

        List<ProductDto> productDtos = mapper.toDtos(products);

        assertNotNull(mapper);
        assertNotNull(productDtos);
        assertEquals(product.getId(), productDtos.get(0).getId());
        assertEquals(product.getProductName(), productDtos.get(0).getProductName());
        assertEquals(product.getPrice(), productDtos.get(0).getPrice());
        assertEquals(product.getQuantity(), productDtos.get(0).getQuantity());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        List<ProductDto> productDtos = Arrays.asList(productDto);

        List<Product> products = mapper.toEntities(productDtos);

        assertNotNull(productDtos);
        assertNotNull(mapper);
        assertNotNull(products);
        assertEquals(products.get(0).getId(), productDto.getId());
        assertEquals(products.get(0).getProductName(), productDto.getProductName());
        assertEquals(products.get(0).getPrice(), productDto.getPrice());
        assertEquals(products.get(0).getQuantity(), productDto.getQuantity());
    }
}