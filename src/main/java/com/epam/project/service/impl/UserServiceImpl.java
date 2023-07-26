package com.epam.project.service.impl;

import com.epam.project.mapper.UserMapper;
import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entity.User;
import com.epam.project.repository.UserRepository;
import com.epam.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with ID= " + id + " in database"));
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
//        if (!userRepository.existsById(userDto.getId())) {
        if (!userRepository.existsById(userDto.getId())) {
            throw new UsernameNotFoundException("There is no user with ID= " + userDto.getId() + " in database");
        }
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
