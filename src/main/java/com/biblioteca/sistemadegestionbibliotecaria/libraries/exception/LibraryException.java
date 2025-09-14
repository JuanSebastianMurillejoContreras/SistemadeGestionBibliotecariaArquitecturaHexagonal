package com.biblioteca.sistemadegestionbibliotecaria.libraries.exception;

import lombok.Getter;

@Getter
public class LibraryException extends RuntimeException  {

    public LibraryException(String message) {
        super(message);
    }
}