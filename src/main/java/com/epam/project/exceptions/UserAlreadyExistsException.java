package com.epam.project.exceptions;

import com.epam.project.exceptions.enums.Code;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@Builder
public class UserAlreadyExistsException extends RuntimeException {

    private final Code codeStatus;
    private final String message;
    private final HttpStatus httpStatus;
}
