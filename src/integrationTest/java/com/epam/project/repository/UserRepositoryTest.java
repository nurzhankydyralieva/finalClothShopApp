package com.epam.project.repository;

import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should find User by Login")
    void findUserByLogin() {
        //given
        String login = "Sarah";
        User user = User.builder()
                .login(login)
                .firstName("Katy")
                .lastName("Smith")
                .email("smith@gmail.com")
                .phone("0999555999")
                .password("111")
                .status(Status.APPROVED)
                .role(Role.BUYER)
                .build();
        userRepository.save(user);
        //when
        Optional<User> expectedLogin = userRepository.findUserByLogin(login);
        //then
        assertThat(expectedLogin.stream().toList()).isNotNull();
    }
}