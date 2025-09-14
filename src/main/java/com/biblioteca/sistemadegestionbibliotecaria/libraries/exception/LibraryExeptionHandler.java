package com.biblioteca.sistemadegestionbibliotecaria.libraries.exception;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.out.LibraryErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class LibraryExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LibraryErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList();
        return new LibraryErrorResponse(errors);
    }

    @ExceptionHandler(LibraryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public LibraryErrorResponse handleLibraryNotFoundException(LibraryNotFoundException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new LibraryErrorResponse(errors);
    }

    @ExceptionHandler(LibraryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public LibraryErrorResponse handleLibraryException(LibraryException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new LibraryErrorResponse(errors);
    }



}