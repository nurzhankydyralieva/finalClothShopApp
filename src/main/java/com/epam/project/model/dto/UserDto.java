package com.epam.project.model.dto;

import com.epam.project.model.entity.Order;
import com.epam.project.model.entity.Product;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
    @Min(0)
    @Max(100)
    private int age;
    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String phone;
    private Status status;
    private Role role;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products;


}
