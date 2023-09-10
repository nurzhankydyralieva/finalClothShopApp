package com.epam.project.service;

import com.epam.project.model.dto.AuthenticationRequest;
import com.epam.project.model.dto.AuthenticationResponse;
import com.epam.project.model.dto.RegisterRequest;
import com.epam.project.model.dto.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthenticationService {
   ResponseEntity<RegistrationResponse> register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
