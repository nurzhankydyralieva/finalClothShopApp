package com.epam.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.epam.project.model.enums.Permission.*;
import static com.epam.project.model.enums.Role.BUYER;
import static com.epam.project.model.enums.Role.VENDOR;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter filter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] AUTH_WHITELIST = {
            "/api/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST)
                .permitAll()
                .requestMatchers("/api/**").hasAnyRole(VENDOR.name(), BUYER.name())

                .requestMatchers(GET, "/api/all").hasAuthority(VENDOR_READ.name())
                .requestMatchers(GET, "/api/{id}").hasAuthority(VENDOR_READ.name())
                .requestMatchers(POST, "/api/save").hasAuthority(VENDOR_CREATE.name())
                .requestMatchers(PUT, "/api/update").hasAuthority(VENDOR_UPDATE.name())
                .requestMatchers(DELETE, "/api/{id}}").hasAuthority(VENDOR_DELETE.name())


                .requestMatchers(GET, "/api/all").hasAuthority(BUYER_READ.name())
                .requestMatchers(GET, "/api/{id}").hasAuthority(BUYER_READ.name())
                .requestMatchers(POST, "/api/save").hasAuthority(BUYER_CREATE.name())
                .requestMatchers(PUT, "/api/update").hasAuthority(BUYER_UPDATE.name())
                .requestMatchers(DELETE, "/api/{id}").hasAuthority(BUYER_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
