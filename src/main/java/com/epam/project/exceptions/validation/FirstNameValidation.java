package com.epam.project.exceptions.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Constraint(validatedBy = FirstNameValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstNameValidation {
    String message() default "{validation.validName}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
