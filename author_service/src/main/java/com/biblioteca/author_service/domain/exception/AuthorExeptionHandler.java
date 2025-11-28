package com.biblioteca.author_service.domain.exception;


import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AuthorExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AuthorErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList();
        return new AuthorErrorResponse(errors);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AuthorErrorResponse handleAuthorNotFoundException(AuthorNotFoundException ex) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        return new AuthorErrorResponse(errors);
    }

    @ExceptionHandler(AuthorException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public AuthorErrorResponse handleAuthorException(AuthorException ex) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        return new AuthorErrorResponse(errors);
    }

}