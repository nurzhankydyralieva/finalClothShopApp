package com.epam.project.exceptions;

import com.epam.project.exceptions.enums.Code;
import com.epam.project.exceptions.model.Error;
import com.epam.project.exceptions.model.ErrorMessageDto;
import com.epam.project.exceptions.model.ErrorRegistration;
import com.epam.project.exceptions.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorMessageDto> validationErrorDetails = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> mapToErrorMessageDto(error))
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(LocalDateTime.now(), Code.BAD_REQUEST, ex.getDetailMessageCode(), validationErrorDetails);
        return new ResponseEntity<>(response, status);
    }


    private ErrorMessageDto mapToErrorMessageDto(ObjectError error) {
        ConstraintViolationImpl<?> source = (ConstraintViolationImpl) error.unwrap(ConstraintViolationImpl.class);
        String fieldError = "";
        String rejectedValue = "";
        if (error instanceof FieldError) {
            fieldError = ((FieldError) error).getField();
            rejectedValue = (String) ((FieldError) error).getRejectedValue();
        }
        return new ErrorMessageDto(error.getObjectName(), fieldError, error.getDefaultMessage(), rejectedValue);
    }


    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorRegistration> handleCommonException(CommonException ex) {
        log.info("Common Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorRegistration.builder().error(Error.builder()
                .code(ex.getCodeStatus())
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorRegistration> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.info("Common Exception: {}", ex.toString());
        return new ResponseEntity<>(ErrorRegistration.builder().error(Error.builder()
                .code(ex.getCodeStatus())
                .message(ex.getMessage())
                .build()).build(), ex.getHttpStatus());
    }
}
