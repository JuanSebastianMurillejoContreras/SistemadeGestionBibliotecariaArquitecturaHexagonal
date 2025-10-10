package com.biblioteca.usuario_service.domain.exception;

import lombok.Getter;

@Getter
public class UsuarioNotFoundException extends UsuarioException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}