package com.biblioteca.library_service.usuario.domain.exception;

import lombok.Getter;

@Getter
public class UsuarioNotFoundException extends UsuarioException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}