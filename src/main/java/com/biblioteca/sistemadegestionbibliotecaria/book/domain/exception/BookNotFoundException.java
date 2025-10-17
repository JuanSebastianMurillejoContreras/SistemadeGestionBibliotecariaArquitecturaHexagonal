package com.biblioteca.library_service.book.domain.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends BookException {
    public BookNotFoundException(String message) {
        super(message);
    }
}