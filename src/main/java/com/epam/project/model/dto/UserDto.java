package com.epam.project.model.dto;

import com.epam.project.model.entity.Order;
import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Token;
import com.epam.project.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    @NotBlank(message = "Login should not be null or empty")
    private String login;
    @NotBlank(message = "Fist name should not be null")
    private String firstName;
    @NotBlank(message = "Last name should not be null")
    private String lastName;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String phone;

    private Role role;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products;
    @JsonIgnore
    private List<Token> tokens;

}
