package com.epam.project.mapper;


import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entitity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtos(List<Order> orders);
    List<Order> toEntities(List<OrderDto> orderDtos);
}
