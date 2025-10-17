package com.biblioteca.library_service.book.domain.exception;


import lombok.Getter;

@Getter
public class BookException extends RuntimeException  {

    public BookException(String message) {
        super(message);
    }
}