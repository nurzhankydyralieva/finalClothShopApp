package com.epam.project.model.dto;

import com.epam.project.model.enums.Status;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private LocalDate shipDate;
    private LocalDate createdAt;
    private Status status;
    private boolean complete;
    private List<ProductDto> items;
}