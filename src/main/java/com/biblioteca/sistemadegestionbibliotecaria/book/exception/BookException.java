package com.biblioteca.sistemadegestionbibliotecaria.book.exception;


import lombok.Getter;

@Getter
public class BookException extends RuntimeException  {

    public BookException(String message) {
        super(message);
    }
}