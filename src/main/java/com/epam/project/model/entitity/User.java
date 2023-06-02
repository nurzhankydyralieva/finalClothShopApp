package com.epam.project.model.entitity;

import com.epam.project.model.enums.Role;

import java.util.List;


public class User {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private List<Order> orders;
    private Role role;
}
