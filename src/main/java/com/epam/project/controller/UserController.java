package com.epam.project.controller;


import com.epam.project.model.dto.UserCreateDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "clothShopApi")
@RequestMapping("/api/users")
@PreAuthorize("hasRole('BUYER')")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('buyer:read')")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    //    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('buyer:read')")
//    public ResponseEntity<UserDto> findById(@PathVariable UUID id) {
//        return ResponseEntity.ok(userService.findById(id));
//    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:read')")
    public ResponseEntity<UserDto> findUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }




    @PostMapping("/save")
    @PreAuthorize("hasAuthority('buyer:create')")
    public ResponseEntity<UserCreateDto> save(@RequestBody @Valid UserCreateDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('buyer:update')")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:delete')")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }

}

