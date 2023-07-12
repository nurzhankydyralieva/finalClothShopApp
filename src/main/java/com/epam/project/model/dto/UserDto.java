package com.epam.project.model.dto;

import com.epam.project.model.entity.Product;
import com.epam.project.model.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String userLoginName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private Set<OrderDto> orders;
    private Set<ProductDto> products;

}
