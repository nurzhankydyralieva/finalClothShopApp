package com.epam.project.service.impl;

import com.epam.project.mapper.ProductMapper;
import com.epam.project.model.dto.ProductDto;
import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Vendor;
import com.epam.project.repository.ProductRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private VendorRepository vendorRepository;
    private List<ProductDto> productDtoList;
    private List<Product> productList;
    private Long productId = 1L;
    private ProductDto productDto;
    private Product product;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository, productMapper, vendorRepository);
        productDtoList = Arrays.asList(ProductDto.builder()
                .id(productId)
                .productName("Jewelry")
                .price(88888)
                .quantity(1)
                .build());
        productList = Arrays.asList(Product.builder()
                .id(productId)
                .productName("Jewelry")
                .price(88888)
                .quantity(1)
                .build());
        productDto = ProductDto.builder()
                .id(productId)
                .productName("Chanel")
                .price(88888)
                .quantity(1)
                .build();
        product = Product.builder()
                .id(productId)
                .productName("Chanel")
                .price(88888)
                .quantity(1)
                .build();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all products in database")
    void findAll() {
        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toDtos(productList)).thenReturn(productDtoList);

        List<ProductDto> allProducts = productService.findAll();

        assertNotNull(allProducts);
        verify(productMapper, times(1)).toDtos(productList);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find product by id")
    void findById() {
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.findProductById(productId)).thenReturn(product);

        ProductDto foundId = productService.findById(product.getId());

        assertNotNull(foundId);
        assertEquals(productId, product.getId());
        verify(productMapper, times(1)).toDto(product);
        verify(productRepository, times(1)).findProductById(product.getId());
    }

    @Test
    @DisplayName("Test should save product to database")
    void save() {
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productRepository.save(product)).thenReturn(product);

        ProductDto savedProduct = productService.save(productDto);

        assertNotNull(savedProduct);
        assertEquals(productDto.getId(), savedProduct.getId());
        assertEquals("Chanel", savedProduct.getProductName());
        assertEquals(88888, savedProduct.getPrice());
        assertEquals(1, savedProduct.getQuantity());
    }

    @Test
    @DisplayName("Test should update product")
    void update() {
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        productDto.setPrice(99999);
        productDto.setProductName("updatedProduct");
        productDto.setQuantity(19);

        ProductDto updatedProduct = productService.save(productDto);

        assertNotNull(updatedProduct);
        assertEquals(productDto, updatedProduct);
        assertEquals("updatedProduct", updatedProduct.getProductName());
        assertEquals(99999, updatedProduct.getPrice());
        assertEquals(19, updatedProduct.getQuantity());
    }

    @Test
    @DisplayName("Test should delete product by vendor id")
    void deleteByVendorId() {
        Vendor vendor = Vendor.builder()
                .id(3L)
                .name("LLC")
                .build();
        Product product = Product.builder()
                .id(1L)
                .productName("Train")
                .price(88888)
                .quantity(20)
                .vendor(vendor)
                .build();
        productRepository.save(product);
        productRepository.deleteByVendorId(3L);

        Product deletedProduct = productRepository.findProductById(product.getId());

        assertThat(deletedProduct).isNull();
    }
}