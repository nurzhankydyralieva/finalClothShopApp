package com.epam.project.model.dto;

import com.epam.project.model.enums.Status;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    private LocalDate shipDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Status status;
    private boolean complete;
    private ProductDto items;
    private UserDto user;
}