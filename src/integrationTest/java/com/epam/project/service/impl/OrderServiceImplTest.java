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
    private List<Order> orders;
    private List<OrderDto> orderDtoList;
    private Order order;
    private OrderDto orderDto;
    private Long orderId =1L;
    private UUID userId = UUID.randomUUID();


    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository, orderMapper, validator, orderUpdateValidator);
        orders = Arrays.asList(Order.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build());
        orderDtoList = Arrays.asList(OrderDto.builder()
                .id(1L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build());
        order = Order.builder()
                .id(orderId)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        orderDto = OrderDto.builder()
                .id(orderId)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find all orders")
    void findAll() {
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDtos(orders)).thenReturn(orderDtoList);

        List<OrderDto> allOrders = orderService.findAll();

        assertNotNull(allOrders);
        verify(orderMapper, times(1)).toDtos(orders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find order by id")
    void findById() {
        when(orderRepository.findOrderById(orderId)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        OrderDto foundOrderById = orderService.findById(order.getId());

        assertNotNull(foundOrderById);
        assertEquals(orderId, order.getId());
        verify(orderMapper, times(1)).toDto(order);
        verify(orderRepository, times(1)).findOrderById(order.getId());
    }

    @Test
    @DisplayName("Test should save order to database")
    void saveOrder() {
        doNothing().when(validator).orderSaveValidation(orderDto, userId);
        when(orderMapper.toEntity(orderDto)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.save(order)).thenReturn(order);

        OrderDto savedOrder = orderService.saveOrder(orderDto, userId);

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
        when(orderMapper.toEntity(orderDto)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.save(order)).thenReturn(order);
        orderDto.setUpdatedAt(LocalDate.now());
        orderDto.setStatus(Status.CANCELLED);

        OrderDto updatedOrder = orderService.saveOrder(orderDto, userId);

        assertNotNull(updatedOrder);
        assertEquals(orderDto, updatedOrder);
        assertEquals(Status.CANCELLED, updatedOrder.getStatus());
    }

    @Test
    @DisplayName("Test should delete order by id")
    void deleteById() {
        orderRepository.save(order);
        orderRepository.deleteById(order.getId());

        OrderDto deletedOrder = orderService.findById(order.getId());

        assertThat(deletedOrder).isNull();
    }
}