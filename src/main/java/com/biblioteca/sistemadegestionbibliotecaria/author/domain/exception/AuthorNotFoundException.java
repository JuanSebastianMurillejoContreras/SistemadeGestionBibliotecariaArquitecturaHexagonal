package com.biblioteca.library_service.author.domain.exception;


public class AuthorNotFoundException extends AuthorException {

    public AuthorNotFoundException(String message) {
        super(message);
    }

}