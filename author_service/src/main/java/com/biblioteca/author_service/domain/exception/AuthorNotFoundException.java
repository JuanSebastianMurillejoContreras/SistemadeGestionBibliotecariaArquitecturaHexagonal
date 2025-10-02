package com.biblioteca.author_service.domain.exception;


public class AuthorNotFoundException extends AuthorException {

    public AuthorNotFoundException(String message) {
        super(message);
    }

}