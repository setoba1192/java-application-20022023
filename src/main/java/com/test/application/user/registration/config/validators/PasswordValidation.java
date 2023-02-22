package com.test.application.user.registration.config.validators;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PasswordValidator.class })
@Documented
public @interface PasswordValidation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}