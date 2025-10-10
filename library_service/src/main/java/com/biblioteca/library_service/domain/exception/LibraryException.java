package com.biblioteca.library_service.domain.exception;

import lombok.Getter;

@Getter
public class LibraryException extends RuntimeException  {

    public LibraryException(String message) {
        super(message);
    }
}