package com.epam.project.service.impl;


import com.epam.project.exceptions.UserNotFoundException;
import com.epam.project.exceptions.validation.user_validation.UserValidation;
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
import java.util.stream.Collectors;

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
    public List<UserDto> filterUserByName() {
        List<User> userList = userRepository.findAll();
        List<User> users = userList.stream()
                .map(user -> new User(
                        user.getId(),
                        user.getLogin(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAge(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getStatus(),
                        user.getRole(),
                        user.getOrders(),
                        user.getProducts()
                ))
                .sorted()
                .limit(4)
                .collect(Collectors.toList());
        return userMapper.toDtos(users);
    }

    @Override
    public List<UserDto> filterUserOlder_18() {
        List<User> userList = userRepository.findAll();
        List<User> userOlder_18 = userList.stream()
                .filter(user -> user.getAge() > 18)
                .sorted()
                .collect(Collectors.toList());
        return userMapper.toDtos(userOlder_18);
    }

    @Override
    public UserDto findUserById(UUID id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            return userMapper.toDto(user);
        } else {
            throw new UserNotFoundException("User with id " + id + "  is not available in database");
        }

    }


    @Override
    public UserCreateDto save(UserCreateDto userCreateDto) {
        userValidation.loginValidator(userCreateDto);
        User user = userMapper.toCreateEntity(userCreateDto);
        //бэкенде поставить статус, чтоб работал
        user.checkStatus();
        user = userRepository.save(user);
        UserCreateDto newUser = userMapper.toCreateDto(user);
        log.info("User saved to database");
        return newUser;
    }


    @Override
    public UserCreateDto update(UserCreateDto userCreateDto) {
        if (!userRepository.existsById(userCreateDto.getId())) {
            throw new UsernameNotFoundException("There is no user with ID= " + userCreateDto.getId() + " in database");
        }
        User user = userMapper.toCreateEntity(userCreateDto);
        user = userRepository.save(user);
        return userMapper.toCreateDto(user);
    }

    @Override
    public void deleteById(UUID id) {
        userValidation.deleteValidator(id);
    }

}
