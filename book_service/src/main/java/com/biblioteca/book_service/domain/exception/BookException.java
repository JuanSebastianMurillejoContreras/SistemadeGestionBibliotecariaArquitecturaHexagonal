package com.biblioteca.book_service.domain.exception;


import lombok.Getter;

@Getter
public class BookException extends RuntimeException  {

    public BookException(String message) {
        super(message);
    }
}