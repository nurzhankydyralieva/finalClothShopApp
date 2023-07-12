package com.epam.project.service.impl;

import com.epam.project.exception.NotFoundException;
import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.AuthenticationResponse;
import com.epam.project.model.entity.User;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no user with ID= " + id + " in database"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (!userRepository.existsById(userDto.getId())) {
            throw new NotFoundException("There is no user with ID= " + userDto.getId() + " in database");
        }
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
