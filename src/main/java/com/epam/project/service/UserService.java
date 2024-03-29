package com.epam.project.service;

import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();

    UserDto findUserById(UUID id);

    UserCreateDto save(UserCreateDto userCreateDto);

    UserCreateDto update(UserCreateDto userCreateDto);

    void deleteById(UUID id);
    List<UserDto> filterUserOlder_18();
    List<UserDto> filterUserByName();
}
