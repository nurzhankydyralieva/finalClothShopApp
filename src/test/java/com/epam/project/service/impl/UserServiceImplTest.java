package com.epam.project.service.impl;

import com.epam.project.exceptions.validation.user_validation.UserValidation;
import com.epam.project.mapper.UserMapper;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.verify;
@DataJpaTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private AutoCloseable autoCloseable;
    private UserMapper userMapper;
    private UserValidation userValidation;
    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserServiceImpl(userRepository, userMapper, userValidation);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAll() {
//        //when
//        underTest.findAll();
//        //then
//        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void findUserById() {
    }

    @Test
    @Disabled
    void save() {
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void deleteById() {
    }
}