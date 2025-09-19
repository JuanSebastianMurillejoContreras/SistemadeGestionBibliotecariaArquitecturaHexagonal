package com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.exception;

import lombok.Getter;

@Getter
public class LibraryNotFoundException extends LibraryException {

    public LibraryNotFoundException(String message) {
        super(message);
    }
}