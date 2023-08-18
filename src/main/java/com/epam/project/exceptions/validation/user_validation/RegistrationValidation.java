package com.epam.project.exceptions.validation.user_validation;

import com.epam.project.exceptions.enums.Code;
import com.epam.project.exceptions.CommonException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationValidation {

    private final Validator validator;

    public <T> void validationRequest(T request) {
        if (request != null) {
            Set<ConstraintViolation<T>> result = validator.validate(request);

            if (!result.isEmpty()){
                String resultValidations = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1,s2)->s1 + ". " + s2).orElse("");
                log.info("Json not valid: {}", resultValidations);
                throw CommonException.builder().codeStatus(Code.REQUEST_VALIDATION_ERROR).message(resultValidations)
                        .httpStatus(HttpStatus.BAD_REQUEST).build();
            }
        }
    }
}
