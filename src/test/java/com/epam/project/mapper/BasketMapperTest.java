package com.epam.project.mapper;

import com.epam.project.model.dto.BasketDto;
import com.epam.project.model.entity.Basket;
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

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        //given
        Basket basket = Basket.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        //when
        BasketDto basketDto = mapper.toDto(basket);
        //then
        assertNotNull(mapper);
        assertNotNull(basketDto);
        assertEquals(basketDto.getId(), basket.getId());
        assertEquals(basketDto.getBasketNumber(), basket.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        //given
        BasketDto basketDto = BasketDto.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        //when
        Basket basket = mapper.toEntity(basketDto);
        //then
        assertNotNull(mapper);
        assertNotNull(basket);
        assertEquals(basket.getId(), basketDto.getId());
        assertEquals(basket.getBasketNumber(), basketDto.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        //given
        Basket basket = Basket.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        List<Basket> baskets = Arrays.asList(basket);
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        //when
        List<BasketDto> basketDtos = mapper.toDtos(baskets);
        //then
        assertNotNull(baskets);
        assertNotNull(mapper);
        assertNotNull(basketDtos);
        assertEquals(basketDtos.get(0).getId(), basket.getId());
        assertEquals(basketDtos.get(0).getBasketNumber(), basket.getBasketNumber());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        //given
        BasketDto basketDto = BasketDto.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        List<BasketDto> basketDtos = Arrays.asList(basketDto);
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        //when
        List<Basket> baskets = mapper.toEntities(basketDtos);
        //then
        assertNotNull(mapper);
        assertNotNull(baskets);
        assertEquals(baskets.get(0).getId(), basketDto.getId());
        assertEquals(baskets.get(0).getBasketNumber(), basketDto.getBasketNumber());
    }
}