package com.epam.project.mapper;

import com.epam.project.model.dto.BasketDto;
import com.epam.project.model.entity.Basket;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BasketMapperTest {

    @Test
    void toDto() {
        Basket basket = Basket.builder()
                .id(1L)
                .basketNumber(33L)
                .build();

        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        assertNotNull(mapper);

        BasketDto basketDto = mapper.toDto(basket);
        assertNotNull(basketDto);
        assertEquals(basketDto.getId(), basket.getId());
        assertEquals(basketDto.getBasketNumber(), basket.getBasketNumber());

    }

    @Test
    void toEntity() {
        BasketDto basketDto = BasketDto.builder()
                .id(1L)
                .basketNumber(33L)
                .build();

        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        assertNotNull(mapper);

        Basket basket = mapper.toEntity(basketDto);
        assertNotNull(basket);
        assertEquals(basket.getId(), basketDto.getId());
        assertEquals(basket.getBasketNumber(), basketDto.getBasketNumber());
    }

    @Test
    void toDtos() {
        Basket basket = Basket.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        List<Basket> baskets = Arrays.asList(basket);
        assertNotNull(baskets);
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        assertNotNull(mapper);

        List<BasketDto> basketDtos = mapper.toDtos(baskets);
        assertNotNull(basketDtos);
        assertEquals(basketDtos.get(0).getId(), basket.getId());
        assertEquals(basketDtos.get(0).getBasketNumber(), basket.getBasketNumber());
    }

    @Test
    void toEntities() {
        BasketDto basketDto = BasketDto.builder()
                .id(1L)
                .basketNumber(33L)
                .build();
        List<BasketDto> basketDtos = Arrays.asList(basketDto);
        BasketMapper mapper = Mappers.getMapper(BasketMapper.class);
        assertNotNull(mapper);

        List<Basket> baskets = mapper.toEntities(basketDtos);
        assertNotNull(baskets);
        assertEquals(baskets.get(0).getId(), basketDto.getId());
        assertEquals(baskets.get(0).getBasketNumber(), basketDto.getBasketNumber());
    }
}