package com.epam.project.service;

import com.epam.project.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAll();

    OrderDto findById(Long id);

    OrderDto save(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);

    void deleteById(Long id);
}
