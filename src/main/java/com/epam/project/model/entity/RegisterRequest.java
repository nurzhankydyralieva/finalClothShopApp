package com.epam.project.model.entity;

import com.epam.project.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long id;
    private String userLoginName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
