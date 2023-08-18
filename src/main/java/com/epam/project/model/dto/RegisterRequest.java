package com.epam.project.model.dto;

import com.epam.project.exceptions.validation.FirstNameValidation;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Login name should not be null")
    @Size(min = 3, message = "Login should be at least 3 chars")
    private String login;
    @NotBlank(message = "Fist name should not be null")
    @Size(min = 3, message = "FirstName should be at least 3 chars")
    @FirstNameValidation
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
    @Enumerated(EnumType.STRING)
    private Status status;
    private Role role;
}
