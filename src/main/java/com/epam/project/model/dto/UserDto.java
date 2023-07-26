package com.epam.project.model.dto;

import com.epam.project.model.entity.Token;
import com.epam.project.model.enums.Role;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String userLoginName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private List<Token> tokens;

}
