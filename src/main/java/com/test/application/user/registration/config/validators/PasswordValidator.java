package com.test.application.user.registration.config.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    public void initialize(PasswordValidation constraintAnnotation) {
        // ...
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regexp = System.getProperty("validation.password.pattern");

        return Optional.ofNullable(value).map(val -> val.matches(regexp)).orElse(false);
    }

}