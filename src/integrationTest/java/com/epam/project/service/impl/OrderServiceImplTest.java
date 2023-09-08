package com.epam.project.service.impl;

import com.epam.project.exceptions.validation.order_validation.OrderSaveValidator;
import com.epam.project.exceptions.validation.order_validation.OrderUpdateValidator;
import com.epam.project.mapper.OrderMapper;
import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.enums.Status;
import com.epam.project.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderSaveValidator validator;
    @Mock
    private OrderUpdateValidator orderUpdateValidator;


    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository, orderMapper, validator, orderUpdateValidator);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all orders")
    void findAll() {
        //given
        List<Order> orders = Arrays.asList(Order.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build());
        List<OrderDto> orderDtoList = Arrays.asList(OrderDto.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build());
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDtos(orders)).thenReturn(orderDtoList);
        //when
        List<OrderDto> allOrders = orderService.findAll();
        //then
        assertNotNull(allOrders);
        verify(orderMapper, times(1)).toDtos(orders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find order by id")
    void findById() {
        //given
        Long orderId = 1L;
        Order order = Order.builder()
                .id(orderId)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .id(orderId)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        when(orderRepository.findOrderById(orderId)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
        //when
        OrderDto foundOrderById = orderService.findById(order.getId());
        //then
        assertNotNull(foundOrderById);
        assertEquals(orderId, order.getId());
        verify(orderMapper, times(1)).toDto(order);
        verify(orderRepository, times(1)).findOrderById(order.getId());
    }

    @Test
    @DisplayName("Test should save order to database")
    void saveOrder() {
        //when
        UUID userId = UUID.randomUUID();
        Order order = Order.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        doNothing().when(validator).orderSaveValidation(orderDto, userId);
        when(orderMapper.toEntity(orderDto)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.save(order)).thenReturn(order);
        //when
        OrderDto savedOrder = orderService.saveOrder(orderDto, userId);
        //then
        assertNotNull(savedOrder);
        assertEquals(orderDto, savedOrder);
        verify(validator, times(1)).orderSaveValidation(orderDto, userId);
        verify(orderMapper, times(1)).toDto(order);
        verify(orderMapper, times(1)).toEntity(orderDto);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    @DisplayName("Test should update order")
    void update() {
        UUID userId = UUID.randomUUID();
        Order order = Order.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        when(orderMapper.toEntity(orderDto)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.save(order)).thenReturn(order);
        orderDto.setUpdatedAt(LocalDate.now());
        orderDto.setStatus(Status.CANCELLED);
        //when
        OrderDto updatedOrder = orderService.saveOrder(orderDto, userId);
        //then
        assertNotNull(updatedOrder);
        assertEquals(orderDto, updatedOrder);
        assertEquals(Status.CANCELLED, updatedOrder.getStatus());
    }

    @Test
    @DisplayName("Test should delete order by id")
    void deleteById() {
        Order order = Order.builder()
                .id(5L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());
        //when
        OrderDto deletedOrder = orderService.findById(order.getId());
        //then
        assertThat(deletedOrder).isNull();
    }
}