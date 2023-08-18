package com.epam.project.service;

import com.epam.project.model.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDto> findAll();

    OrderDto findById(Long id);

//    OrderDto save(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);

    void deleteById(Long id);
    OrderDto saveOrder(OrderDto orderDto, UUID id);
}
