package com.epam.project.service.impl;

import com.epam.project.exceptions.validation.user_validation.UserValidation;
import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import com.epam.project.repository.UserRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserValidation userValidation;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, userMapper, userValidation);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test should return all user")
    void findAll() {
        //given
        UUID userId = UUID.randomUUID();
        List<UserDto> userDtoList = Arrays.asList(
                UserDto.builder()
                        .id(userId)
                        .login("Tom")
                        .firstName("Olson")
                        .lastName("Smith")
                        .email("olson@gmail.com")
                        .phone("0999999999")
                        .status(Status.APPROVED)
                        .role(Role.BUYER)
                        .build());
        List<User> userList = Arrays.asList(
                User.builder()
                        .id(userId)
                        .login("Tom")
                        .firstName("Olson")
                        .lastName("Smith")
                        .email("olson@gmail.com")
                        .phone("0999999999")
                        .status(Status.APPROVED)
                        .role(Role.BUYER)
                        .build());
        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toDtos(userList)).thenReturn(userDtoList);
        //when
        List<UserDto> allUsers = userService.findAll();
        //then
        assertNotNull(allUsers);
        verify(userMapper, times(1)).toDtos(userList);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test should find user by Id")
    void findUserById() {
        //given
        UUID userId = UUID.randomUUID();
        UserDto userDto = UserDto.builder()
                .id(userId)
                .login("Tom")
                .firstName("Olson")
                .lastName("Smith")
                .email("olson@gmail.com")
                .phone("0999999999")
                .status(Status.APPROVED)
                .role(Role.BUYER)
                .build();
        User user = User.builder()
                .id(userId)
                .login("Tom")
                .firstName("Olson")
                .lastName("Smith")
                .email("olson@gmail.com")
                .phone("0999999999")
                .status(Status.APPROVED)
                .role(Role.BUYER)
                .build();
        when(userRepository.findUserById(userId)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);
        //when
        UserDto userById = userService.findUserById(user.getId());
        //then
        assertNotNull(userById);
        assertEquals(userId, user.getId());
        verify(userMapper, times(1)).toDto(user);
        verify(userRepository, times(1)).findUserById(user.getId());
    }

    @Test
    @DisplayName("Test should save new user to database")
    void save() {
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .login("Sultan")
                .firstName("Suleiman")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555999555")
                .password("111")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Sultan")
                .firstName("Suleiman")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555999555")
                .password("111")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        doNothing().when(userValidation).loginValidator(userCreateDto);
        when(userMapper.toCreateEntity(userCreateDto)).thenReturn(user);
        when(userMapper.toCreateDto(user)).thenReturn(userCreateDto);
        when(userRepository.save(user)).thenReturn(user);
        //when
        UserCreateDto savedUser = userService.save(userCreateDto);
        //then
        assertNotNull(savedUser);
        assertEquals(userCreateDto, savedUser);
        verify(userValidation, times(1)).loginValidator(userCreateDto);
        verify(userMapper, times(1)).toCreateEntity(userCreateDto);
        verify(userMapper, times(1)).toCreateDto(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test should update user in database")
    void update() {
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .login("Sultan")
                .firstName("Suleiman")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555999555")
                .password("111")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Sultan")
                .firstName("Suleiman")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555999555")
                .password("111")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        when(userMapper.toCreateEntity(userCreateDto)).thenReturn(user);
        when(userMapper.toCreateDto(user)).thenReturn(userCreateDto);
        when(userRepository.save(user)).thenReturn(user);
        userCreateDto.setFirstName("Mariya");
        userCreateDto.setLogin("SuperStar");
        //when
        UserCreateDto updatedUser = userService.save(userCreateDto);
        //then
        assertNotNull(updatedUser);
        assertEquals(userCreateDto, updatedUser);
        assertEquals("Mariya", updatedUser.getFirstName());
        assertEquals("SuperStar", updatedUser.getLogin());
    }

    @Test
    @DisplayName("Test should delete user by id")
    void deleteById() {
        //given
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Sultan")
                .firstName("Suleiman")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555999555")
                .password("111")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        //when
        User deletedUser = userRepository.findUserById(user.getId());
        //then
        assertThat(deletedUser).isNull();
    }
}