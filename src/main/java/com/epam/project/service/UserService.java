package com.epam.project.service;

import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();

    UserDto findUserById(UUID id);

    UserCreateDto save(UserCreateDto userCreateDto);

    UserDto update(UserDto userDto);

    void deleteById(UUID id);
}
