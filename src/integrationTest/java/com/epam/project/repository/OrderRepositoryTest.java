package com.epam.project.repository;

import com.epam.project.model.entity.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application.properties")
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    private Order order;

    @BeforeEach
    void setUp() {
        order = Order.builder()
                .shipDate(LocalDate.now().minusDays(2))
                .createdAt(LocalDate.now().minusDays(5))
                .complete(true)
                .updatedAt(LocalDate.now())
                .build();
        orderRepository.save(order);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find order by id")
    void findOrderById() {
        Order orderById = orderRepository.findOrderById(order.getId());

        assertThat(orderById).isNotNull();
    }
}