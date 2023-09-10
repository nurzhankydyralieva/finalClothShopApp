package com.epam.project.mapper;

import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class OrderMapperTest {
    private OrderMapper mapper;
    private Order order;
    private OrderDto orderDto;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(OrderMapper.class);
        order = Order.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        orderDto = OrderDto.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        OrderDto orderDto = mapper.toDto(order);

        assertNotNull(mapper);
        assertNotNull(orderDto);
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getShipDate(), orderDto.getShipDate());
        assertEquals(order.getCreatedAt(), orderDto.getCreatedAt());
        assertEquals(order.getUpdatedAt(), orderDto.getUpdatedAt());
        assertEquals(order.getStatus(), orderDto.getStatus());

    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        Order order = mapper.toEntity(orderDto);

        assertNotNull(mapper);
        assertNotNull(order);
        assertEquals(orderDto.getId(), order.getId());
        assertEquals(orderDto.getShipDate(), order.getShipDate());
        assertEquals(orderDto.getCreatedAt(), order.getCreatedAt());
        assertEquals(orderDto.getUpdatedAt(), order.getUpdatedAt());
        assertEquals(orderDto.getStatus(), order.getStatus());
        assertEquals(orderDto.isComplete(), order.isComplete());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        List<Order> orders = Arrays.asList(order);

        List<OrderDto> orderDtos = mapper.toDtos(orders);

        assertNotNull(orders);
        assertNotNull(mapper);
        assertNotNull(orderDtos);
        assertEquals(order.getId(), orderDtos.get(0).getId());
        assertEquals(order.getShipDate(), orderDtos.get(0).getShipDate());
        assertEquals(order.getCreatedAt(), orderDtos.get(0).getCreatedAt());
        assertEquals(order.getUpdatedAt(), orderDtos.get(0).getUpdatedAt());
        assertEquals(order.getStatus(), orderDtos.get(0).getStatus());
        assertEquals(order.isComplete(), orderDtos.get(0).isComplete());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        List<OrderDto> orderDtos = Arrays.asList(orderDto);

        List<Order> orders = mapper.toEntities(orderDtos);

        assertNotNull(orderDtos);
        assertNotNull(mapper);
        assertNotNull(orders);
        assertEquals(orderDto.getId(), orders.get(0).getId());
        assertEquals(orderDto.getShipDate(), orders.get(0).getShipDate());
        assertEquals(orderDto.getCreatedAt(), orders.get(0).getCreatedAt());
        assertEquals(orderDto.getUpdatedAt(), orders.get(0).getUpdatedAt());
        assertEquals(orderDto.getStatus(), orders.get(0).getStatus());
        assertEquals(orderDto.isComplete(), orders.get(0).isComplete());
    }
}