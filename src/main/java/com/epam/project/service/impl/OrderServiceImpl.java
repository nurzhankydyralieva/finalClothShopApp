package com.epam.project.service.impl;

import com.epam.project.mapper.OrderMapper;
import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.repository.OrderRepository;
import com.epam.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.toDtos(orderRepository.findAll());
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The order with id" + id + " not found"));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        if (!orderRepository.existsById(orderDto.getId())) {
            throw new RuntimeException("Order not found");
        }
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
