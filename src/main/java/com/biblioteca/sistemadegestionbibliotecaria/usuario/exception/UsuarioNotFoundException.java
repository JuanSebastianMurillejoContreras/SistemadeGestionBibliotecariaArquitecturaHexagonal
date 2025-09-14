package com.biblioteca.sistemadegestionbibliotecaria.usuario.exception;

import lombok.Getter;

@Getter
public class UsuarioNotFoundException extends UsuarioException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}