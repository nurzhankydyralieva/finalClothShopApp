package com.epam.project.exceptions.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDto {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;

}
