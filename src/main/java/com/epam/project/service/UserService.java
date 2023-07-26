package com.epam.project.service;

import com.epam.project.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(UUID id);

    UserDto save(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(UUID id);
}
