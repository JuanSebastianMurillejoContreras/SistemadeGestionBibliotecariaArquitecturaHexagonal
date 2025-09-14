package com.biblioteca.sistemadegestionbibliotecaria.usuario.exception;


import lombok.Getter;

@Getter
public class UsuarioException extends RuntimeException  {

    public UsuarioException(String message) {
        super(message);
    }
}