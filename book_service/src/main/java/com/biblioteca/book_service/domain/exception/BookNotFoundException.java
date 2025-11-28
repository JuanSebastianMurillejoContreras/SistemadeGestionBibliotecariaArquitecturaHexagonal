package com.biblioteca.book_service.domain.exception;

import lombok.Getter;

@Getter
public class BookNotFoundException extends BookException {

    public BookNotFoundException(String message) {
        super(message);
    }

}