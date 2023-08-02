package com.epam.project.service.impl;


import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

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
        return null;
    }


        @Override
    public UserCreateDto save(UserCreateDto userCreateDto){
        User user = userMapper.toCreateEntity(userCreateDto);
        user = userRepository.save(user);
        UserCreateDto newUser = userMapper.toCreateDto(user);
        logger.log(Level.INFO, "User saved to database");
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
        User user = userRepository.findUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            logger.log(Level.INFO, "User with id: " + id + " deleted");
        } else {
            throw new UsernameNotFoundException("User with id: " + id + " not available in database");
        }
    }


}
