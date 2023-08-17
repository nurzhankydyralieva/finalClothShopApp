package com.epam.project.service.impl;


import com.epam.project.exceptions.UserNotFoundException;
import com.epam.project.exceptions.validation.UserValidation;
import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation userValidation;

    @Override
    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    @Override
    public UserDto findUserById(UUID id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            return userMapper.toDto(user);
        }
        throw new UserNotFoundException("User with id " + id + "  is not available in database");
    }

    @Override
    public UserCreateDto save(UserCreateDto userCreateDto) {
        userValidation.loginValidator(userCreateDto);
        User user = userMapper.toCreateEntity(userCreateDto);
        user = userRepository.save(user);
        UserCreateDto newUser = userMapper.toCreateDto(user);
        log.info("User saved to database");
        return newUser;
    }


    @Override
    public UserDto update(UserDto userDto) {
        if (!userRepository.existsById(userDto.getId())) {
            throw new UsernameNotFoundException("There is no user with ID= " + userDto.getId() + " in database");
        }
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(UUID id) {
        userValidation.deleteValidator(id);
    }

    @Override
    public List<UserDto> findByFirstName() {
        var foundFirstName = userRepository.findUserByFirstName();
        return userMapper.toDtos(foundFirstName);
    }


}
