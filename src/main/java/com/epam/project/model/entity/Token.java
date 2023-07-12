package com.epam.project.model.entity;

import com.epam.project.model.enums.TokenType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String token;
    public boolean revoked;
    public boolean expired;
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType = TokenType.BEARER;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public User user;
}
