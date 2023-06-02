package com.epam.project.mapper;

import com.epam.project.model.dto.UserDto;
import com.epam.project.model.entitity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtos(List<User> users);
    List<User> toEntities(List<UserDto> userDtos);
}
