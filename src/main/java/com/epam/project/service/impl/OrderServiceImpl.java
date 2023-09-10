package com.epam.project.service.impl;

import com.epam.project.aspect.annotation.OrderSaveAspectAnnotation;
import com.epam.project.aspect.annotation.OrderUpdateAspectAnnotation;
import com.epam.project.exceptions.validation.order_validation.OrderSaveValidator;
import com.epam.project.exceptions.validation.order_validation.OrderUpdateValidator;
import com.epam.project.mapper.OrderMapper;
import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.enums.Status;
import com.epam.project.repository.OrderRepository;
import com.epam.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderSaveValidator validator;
    private final OrderUpdateValidator orderUpdateValidator;

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.toDtos(orderRepository.findAll());
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findOrderById(id);
        return orderMapper.toDto(order);
    }

    // если пользователь заблокирован, то ему нельзя создавать заказы
    @OrderSaveAspectAnnotation
    @Override
    public OrderDto saveOrder(OrderDto orderDto, UUID id) {
        validator.orderSaveValidation(orderDto, id);
        Order order = orderMapper.toEntity(orderDto);
        //бэкенде поставить статус, чтоб работал
        order.statusWhenCreatingAnOrder();
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @OrderUpdateAspectAnnotation
    @Override
    public OrderDto update(OrderDto orderDto, UUID id) {
        Order order = orderMapper.toEntity(orderDto);
        orderUpdateValidator.orderUpdateValidation(orderDto, id);
        order.setUpdatedAt(LocalDate.now());
        order.setStatus(Status.APPROVED);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);

    }

}
