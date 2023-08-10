package com.epam.project.exceptions.model;

import com.epam.project.exceptions.enums.Code;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime date;
    private Code code;
    private String message;
    private List<ErrorMessageDto> errors;


}
