package com.epam.project.mapper;


import com.epam.project.model.dto.CourierDto;
import com.epam.project.model.entitity.Courier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierDto toDto(Courier courier);

    Courier toEntity(CourierDto courierDto);

    List<CourierDto> toDtos(List<Courier> couriers);

    List<Courier> toEntities(List<CourierDto> courierDtos);

}
