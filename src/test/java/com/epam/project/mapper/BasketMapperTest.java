package com.epam.project.mapper;

import com.epam.project.model.dto.BasketDto;
import com.epam.project.model.entity.Basket;
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
class BasketMapperTest {
    private BasketMapper mapper;
    private Basket basket;
    private BasketDto basketDto;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(BasketMapper.class);
        basket = Basket.builder().id(1L).basketNumber(33L).build();
        basketDto = BasketDto.builder().id(1L).basketNumber(33L).build();
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        BasketDto basketDto = mapper.toDto(basket);

        assertNotNull(mapper);
        assertNotNull(basketDto);
        assertEquals(basketDto.getId(), basket.getId());
        assertEquals(basketDto.getBasketNumber(), basket.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        Basket basket = mapper.toEntity(basketDto);

        assertNotNull(mapper);
        assertNotNull(basket);
        assertEquals(basket.getId(), basketDto.getId());
        assertEquals(basket.getBasketNumber(), basketDto.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        List<Basket> baskets = Arrays.asList(basket);

        List<BasketDto> basketDtos = mapper.toDtos(baskets);

        assertNotNull(baskets);
        assertNotNull(mapper);
        assertNotNull(basketDtos);
        assertEquals(basketDtos.get(0).getId(), basket.getId());
        assertEquals(basketDtos.get(0).getBasketNumber(), basket.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        List<BasketDto> basketDtos = Arrays.asList(basketDto);

        List<Basket> baskets = mapper.toEntities(basketDtos);

        assertNotNull(mapper);
        assertNotNull(baskets);
        assertEquals(baskets.get(0).getId(), basketDto.getId());
        assertEquals(baskets.get(0).getBasketNumber(), basketDto.getBasketNumber());
    }
}