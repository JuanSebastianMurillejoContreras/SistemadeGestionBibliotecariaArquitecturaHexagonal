package com.biblioteca.book_service.domain.exception;

import com.biblioteca.sistemadegestionbibliotecaria.book.domain.exception.BookException;
import lombok.Getter;

@Getter
public class BookNotFoundException extends BookException {
    public BookNotFoundException(String message) {
        super(message);
    }
}