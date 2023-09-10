package com.epam.project.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasketDto {
    private Long id;
    private Long basketNumber;
}
