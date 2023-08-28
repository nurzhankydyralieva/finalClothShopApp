package com.epam.project.mapper;

import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserMapperTest {

    @Test
    void toDto() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Max")
                .firstName("Maxim")
                .lastName("Sokolov")
                .email("max@gmail.com")
                .phone("0555000555")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();

        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        UserDto userDto = mapper.toDto(user);
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
    void toEntity() {
        UserDto userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .login("Sultan")
                .firstName("Maxmud")
                .lastName("Khan")
                .email("khan@gmail.com")
                .phone("0555000555")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        User user = mapper.toEntity(userDto);
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
    void toDtos() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Sarah")
                .firstName("Jesika")
                .lastName("Parker")
                .email("sarah@gmail.com")
                .phone("0555000555")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        List<User> users = Arrays.asList(user);

        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        List<UserDto> userDtos = mapper.toDtos(users);
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
    void toEntities() {
        UserDto userDto = UserDto.builder()
                .id(UUID.randomUUID())
                .login("Mendi")
                .firstName("Moon")
                .lastName("Laletty")
                .email("moon@gmail.com")
                .phone("0999099999")
                .status(Status.APPROVED)
                .role(Role.VENDOR)
                .build();

        List<UserDto> userDtos = Arrays.asList(userDto);

        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        List<User> users = mapper.toEntities(userDtos);
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
    void toCreateDto() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .login("Olson")
                .firstName("Sandra")
                .lastName("Smith")
                .email("olson@gmail.com")
                .phone("0555000555")
                .password("111")
                .role(Role.BUYER)
                .build();

        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        UserCreateDto userCreateDto = mapper.toCreateDto(user);
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
    void toCreateEntity() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .id(UUID.randomUUID())
                .login("Olson")
                .firstName("Sandra")
                .lastName("Smith")
                .email("olson@gmail.com")
                .phone("0555000555")
                .password("111")
                .role(Role.BUYER)
                .build();
        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        assertNotNull(mapper);

        User user = mapper.toCreateEntity(userCreateDto);
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