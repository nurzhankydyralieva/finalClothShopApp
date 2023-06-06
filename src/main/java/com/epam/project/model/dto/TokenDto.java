package com.epam.project.model.dto;

import com.epam.project.model.entitity.User;
import com.epam.project.model.enums.TokenType;
import lombok.Data;

@Data
public class TokenDto {
    public Long id;
    public String token;
    public boolean revoked;
    public boolean expired;
    public TokenType tokenType = TokenType.BEARER;
    public User user;
}
