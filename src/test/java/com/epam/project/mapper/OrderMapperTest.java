package com.epam.project.mapper;

import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.entity.Order;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OrderMapperTest {

    @Test
    void toDto() {
        Order order = Order.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
        assertNotNull(orderMapper);

        OrderDto orderDto = orderMapper.toDto(order);
        assertNotNull(orderDto);
        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getShipDate(), orderDto.getShipDate());
        assertEquals(order.getCreatedAt(), orderDto.getCreatedAt());
        assertEquals(order.getUpdatedAt(), orderDto.getUpdatedAt());
        assertEquals(order.getStatus(), orderDto.getStatus());

    }

    @Test
    void toEntity() {
        OrderDto orderDto = OrderDto.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
        assertNotNull(orderMapper);

        Order order = orderMapper.toEntity(orderDto);
        assertNotNull(order);
        assertEquals(orderDto.getId(), order.getId());
        assertEquals(orderDto.getShipDate(), order.getShipDate());
        assertEquals(orderDto.getCreatedAt(), order.getCreatedAt());
        assertEquals(orderDto.getUpdatedAt(), order.getUpdatedAt());
        assertEquals(orderDto.getStatus(), order.getStatus());
        assertEquals(orderDto.isComplete(), order.isComplete());
    }

    @Test
    void toDtos() {
        Order order = Order.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        List<Order> orders = Arrays.asList(order);
        assertNotNull(orders);

        OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
        assertNotNull(orderMapper);
        List<OrderDto> orderDtos = orderMapper.toDtos(orders);
        assertNotNull(orderDtos);
        assertEquals(order.getId(), orderDtos.get(0).getId());
        assertEquals(order.getShipDate(), orderDtos.get(0).getShipDate());
        assertEquals(order.getCreatedAt(), orderDtos.get(0).getCreatedAt());
        assertEquals(order.getUpdatedAt(), orderDtos.get(0).getUpdatedAt());
        assertEquals(order.getStatus(), orderDtos.get(0).getStatus());
        assertEquals(order.isComplete(), orderDtos.get(0).isComplete());
    }

    @Test
    void toEntities() {
        OrderDto orderDto = OrderDto.builder()
                .id(2L)
                .shipDate(LocalDate.now())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .status(Status.APPROVED)
                .complete(true)
                .build();
        List<OrderDto> orderDtos = Arrays.asList(orderDto);
        assertNotNull(orderDtos);

        OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
        assertNotNull(orderMapper);
        List<Order> orders = orderMapper.toEntities(orderDtos);
        assertNotNull(orders);
        assertEquals(orderDto.getId(), orders.get(0).getId());
        assertEquals(orderDto.getShipDate(), orders.get(0).getShipDate());
        assertEquals(orderDto.getCreatedAt(), orders.get(0).getCreatedAt());
        assertEquals(orderDto.getUpdatedAt(), orders.get(0).getUpdatedAt());
        assertEquals(orderDto.getStatus(), orders.get(0).getStatus());
        assertEquals(orderDto.isComplete(), orders.get(0).isComplete());
    }
}