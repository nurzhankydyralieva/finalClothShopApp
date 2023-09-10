package com.epam.project.exceptions;

import com.epam.project.exceptions.model.Error;
import com.epam.project.exceptions.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex) {
        log.info("Common Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .code(ex.getCodeStatus())
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.info("User Already Exists Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .code(ex.getCodeStatus())
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(StatusNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleStatusNotAllowedException(StatusNotAllowedException ex) {
        log.info("Status Not Allowed Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .code(ex.getCodeStatus())
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }
}
