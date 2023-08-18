package com.epam.project.service.impl;

import com.epam.project.exceptions.StatusNotAllowedException;
import com.epam.project.exceptions.validation.order_validation.OrderSaveValidator;
import com.epam.project.mapper.OrderMapper;
import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Status;
import com.epam.project.repository.OrderRepository;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderSaveValidator validator;

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
    public OrderDto saveOrder(OrderDto orderDto, UUID id) {
        Order order = orderMapper.toEntity(orderDto);
        validator.orderSaveValidation(orderDto, id);
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
