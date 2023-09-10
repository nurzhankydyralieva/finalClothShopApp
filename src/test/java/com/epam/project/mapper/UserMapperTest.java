package com.epam.project.mapper;

import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class UserMapperTest {
    private UserMapper mapper;
    private User user;
    private UserDto userDto;
    private UserCreateDto userCreateDto;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(UserMapper.class);
        user = User.builder()
                .id(UUID.randomUUID())
                .login("Max")
                .firstName("Maxim")
                .lastName("Sokolov")
                .email("max@gmail.com")
                .phone("0555000555")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .login("Sultan")
                .firstName("Maxmud")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555000555")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        userCreateDto = UserCreateDto.builder()
                .id(UUID.randomUUID())
                .login("Olson")
                .firstName("Sandra")
                .lastName("Smith")
                .email("olson@gmail.com")
                .phone("0555000555")
                .password("111")
                .role(Role.BUYER)
                .build();
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        UserDto userDto = mapper.toDto(user);

        assertNotNull(mapper);
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getLogin(), userDto.getLogin());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPhone(), userDto.getPhone());
        assertEquals(user.getStatus(), userDto.getStatus());
        assertEquals(user.getRole(), userDto.getRole());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        User user = mapper.toEntity(userDto);

        assertNotNull(mapper);
        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getLogin(), user.getLogin());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPhone(), user.getPhone());
        assertEquals(userDto.getStatus(), user.getStatus());
        assertEquals(userDto.getRole(), user.getRole());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        List<User> users = Arrays.asList(user);

        List<UserDto> userDtos = mapper.toDtos(users);

        assertNotNull(mapper);
        assertNotNull(userDtos);
        assertEquals(user.getId(), userDtos.get(0).getId());
        assertEquals(user.getLogin(), userDtos.get(0).getLogin());
        assertEquals(user.getFirstName(), userDtos.get(0).getFirstName());
        assertEquals(user.getLastName(), userDtos.get(0).getLastName());
        assertEquals(user.getEmail(), userDtos.get(0).getEmail());
        assertEquals(user.getPhone(), userDtos.get(0).getPhone());
        assertEquals(user.getStatus(), userDtos.get(0).getStatus());
        assertEquals(user.getRole(), userDtos.get(0).getRole());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        List<UserDto> userDtos = Arrays.asList(userDto);

        List<User> users = mapper.toEntities(userDtos);

        assertNotNull(mapper);
        assertNotNull(users);
        assertEquals(users.get(0).getId(), userDto.getId());
        assertEquals(users.get(0).getLogin(), userDto.getLogin());
        assertEquals(users.get(0).getFirstName(), userDto.getFirstName());
        assertEquals(users.get(0).getLastName(), userDto.getLastName());
        assertEquals(users.get(0).getEmail(), userDto.getEmail());
        assertEquals(users.get(0).getPhone(), userDto.getPhone());
        assertEquals(users.get(0).getStatus(), userDto.getStatus());
        assertEquals(users.get(0).getRole(), userDto.getRole());
    }

    @Test
    @DisplayName("Test should map to create user dto")
    void toCreateDto() {
        UserCreateDto userCreateDto = mapper.toCreateDto(user);

        assertNotNull(mapper);
        assertNotNull(userCreateDto);
        assertEquals(user.getId(), userCreateDto.getId());
        assertEquals(user.getLogin(), userCreateDto.getLogin());
        assertEquals(user.getFirstName(), userCreateDto.getFirstName());
        assertEquals(user.getLastName(), userCreateDto.getLastName());
        assertEquals(user.getEmail(), userCreateDto.getEmail());
        assertEquals(user.getPhone(), userCreateDto.getPhone());
        assertEquals(user.getPassword(), userCreateDto.getPassword());
        assertEquals(user.getRole(), userCreateDto.getRole());
    }

    @Test
    @DisplayName("Test should map to create entities")
    void toCreateEntity() {
        User user = mapper.toCreateEntity(userCreateDto);

        assertNotNull(mapper);
        assertNotNull(user);
        assertEquals(userCreateDto.getId(), user.getId());
        assertEquals(userCreateDto.getLogin(), user.getLogin());
        assertEquals(userCreateDto.getFirstName(), user.getFirstName());
        assertEquals(userCreateDto.getLastName(), user.getLastName());
        assertEquals(userCreateDto.getEmail(), user.getEmail());
        assertEquals(userCreateDto.getPhone(), user.getPhone());
        assertEquals(userCreateDto.getPassword(), user.getPassword());
        assertEquals(userCreateDto.getRole(), user.getRole());
    }
}