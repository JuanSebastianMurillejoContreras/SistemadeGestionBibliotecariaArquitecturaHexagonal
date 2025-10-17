package com.biblioteca.usuario_service.infraestructura.controller.exception;

import com.biblioteca.library_service.usuario.domain.exception.UsuarioException;
import com.biblioteca.library_service.usuario.domain.exception.UsuarioNotFoundException;
import com.biblioteca.library_service.usuario.infraestructura.controller.dto.out.UsuarioErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsuarioExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UsuarioErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .toList().toString();
        return new UsuarioErrorResponse(errors);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UsuarioErrorResponse handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return new UsuarioErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UsuarioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public UsuarioErrorResponse handleUsuarioException(UsuarioException ex) {
        return new UsuarioErrorResponse(ex.getMessage());
    }

}
