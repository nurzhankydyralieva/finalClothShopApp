package com.epam.project.mapper;


import com.epam.project.model.dto.BasketDto;
import com.epam.project.model.entitity.Basket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketMapper {
    BasketDto toDto(Basket basket);

    Basket toEntity(BasketDto basketDto);

    List<BasketDto> toDtos(List<Basket> baskets);

    List<Basket> toEntities(List<BasketDto> basketDtos);
}
