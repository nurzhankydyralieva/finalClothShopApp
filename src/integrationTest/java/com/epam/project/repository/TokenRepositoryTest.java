package com.epam.project.repository;

import com.epam.project.model.entity.Token;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import com.epam.project.model.enums.TokenType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application.properties")
class TokenRepositoryTest {

    @Autowired
    private TokenRepository tokenRepository;
    private Token token;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Sarah")
                .firstName("Katy")
                .lastName("Smith")
                .email("smith@gmail.com")
                .phone("0999555999")
                .password("123")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        token = Token.builder()
                .id(UUID.randomUUID())
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYXJhaCIsImlhdCI6MTY5NDM0ODU0NSwiZXhwIjoxNjk0NDM0OTQ1fQ.BANGwwml-CM_EbQJIKX0huL5YMBhG0lrhskF69hdeIw")
                .tokenType(TokenType.BEARER)
                .revoked(true)
                .expired(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }

    @AfterEach
    void tearDown() {
        tokenRepository.deleteAll();
    }

    @Test
    void findAllValidTokenByUser() {
        List<Token> allValidTokenByUser = tokenRepository.findAllValidTokenByUser(token.getId());

        assertThat(allValidTokenByUser).isNotNull();
    }

    @Test
    @DisplayName("Test should find by token")
    void findByToken() {
        Optional<Token> byToken = tokenRepository.findByToken(token.getToken());

        assertThat(byToken).isNotNull();
    }
}