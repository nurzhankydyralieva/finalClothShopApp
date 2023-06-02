package com.epam.project.service;

import com.epam.project.model.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto save(UserDto userDto);

    UserDto update(UserDto userDto);

    void deleteById(Long id);
}
