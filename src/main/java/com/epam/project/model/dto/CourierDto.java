package com.epam.project.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String idNumber;
}
