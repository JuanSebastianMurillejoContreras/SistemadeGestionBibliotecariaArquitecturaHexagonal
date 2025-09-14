package com.biblioteca.sistemadegestionbibliotecaria.reservation.exception;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.out.ReservationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReservationErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList().toString();
        return new ReservationErrorResponse(errors);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReservationErrorResponse handleReservationNotFoundException(ReservationNotFoundException ex) {
        return new ReservationErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ReservationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ReservationErrorResponse handleReservationException(ReservationException ex) {
        return new ReservationErrorResponse(ex.getMessage());
    }

}
