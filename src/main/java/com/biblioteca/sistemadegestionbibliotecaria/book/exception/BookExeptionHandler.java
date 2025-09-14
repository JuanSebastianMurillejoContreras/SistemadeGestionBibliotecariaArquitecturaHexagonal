package com.biblioteca.sistemadegestionbibliotecaria.book.exception;

import com.biblioteca.sistemadegestionbibliotecaria.book.dto.out.BookErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BookErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList().toString();
        return new BookErrorResponse(errors);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BookErrorResponse handleBookNoNotificationsFoundException(BookNotFoundException ex) {
        return new BookErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(BookException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BookErrorResponse handleBookException(BookException ex) {
        return new BookErrorResponse(ex.getMessage());
    }

}
