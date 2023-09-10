package com.epam.project.exceptions.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstNameValidation,String> {
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if(name == null){
            return true;
        }
        if (name.matches("^[a-zA-Z\\s]*$")){
            return true;
        }
        return false;
    }
}
