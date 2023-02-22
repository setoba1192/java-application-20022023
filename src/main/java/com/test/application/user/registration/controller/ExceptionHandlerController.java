package com.test.application.user.registration.controller;

import com.test.application.user.registration.dto.RespuestaGenericaDTO;
import com.test.application.user.registration.exception.ServiceException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public RespuestaGenericaDTO constraintsValidationHandler(ConstraintViolationException e) {

        List<String> errors = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage()).toList();

        return RespuestaGenericaDTO.builder()
                .mensaje(this.messageSource.getMessage("global.error.found", null, Locale.getDefault()))
                .errores(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespuestaGenericaDTO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error ->error.getDefaultMessage()).toList();

        return RespuestaGenericaDTO.builder()
                .mensaje(this.messageSource.getMessage("global.error.found", null, Locale.getDefault()))
                .errores(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public RespuestaGenericaDTO serviceExceptionHandler(ServiceException e) {
        return RespuestaGenericaDTO.builder()
                .mensaje(e.getMessage())
                .build();
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespuestaGenericaDTO handleAuthenticationException(Exception ex) {
        return RespuestaGenericaDTO.builder()
                .mensaje(this.messageSource.getMessage("login.error", null, Locale.getDefault()))
                .build();
    }

}
