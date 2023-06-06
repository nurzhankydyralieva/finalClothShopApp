package com.epam.project.service;

import com.epam.project.model.entitity.AuthenticationRequest;
import com.epam.project.model.entitity.AuthenticationResponse;
import com.epam.project.model.entitity.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
