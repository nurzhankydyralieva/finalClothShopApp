package com.epam.project.service;

import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> findAll();

    OrderDto findById(Long id);

    OrderDto update(OrderDto orderDto, UUID id);

    void deleteById(Long id);

    OrderDto saveOrder(OrderDto orderDto, UUID id);
}
