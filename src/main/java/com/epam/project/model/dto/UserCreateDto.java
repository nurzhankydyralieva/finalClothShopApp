package com.epam.project.model.dto;


import com.epam.project.model.entity.Order;
import com.epam.project.model.entity.Product;
import com.epam.project.model.entity.Token;
import com.epam.project.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private UUID id;
    @NotBlank(message = "Login name should not be null")
    @Size(min = 3, message = "Login should be at least 3 chars")
    private String login;
    @NotBlank(message = "Fist name should not be null")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    private String firstName;
    @NotBlank(message = "Last name should not be null")
    @Size(min = 3, message = "LastName should be at least 3 chars")
    private String lastName;
    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String phone;
    @NotBlank(message = "Password name should not be null")
    private String password;
    private Role role;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products;
    @JsonIgnore
    private List<Token> tokens;

}
