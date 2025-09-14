package com.biblioteca.sistemadegestionbibliotecaria.book.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends BookException {
    public BookNotFoundException(String message) {
        super(message);
    }
}