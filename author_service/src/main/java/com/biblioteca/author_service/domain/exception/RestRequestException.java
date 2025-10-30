package com.biblioteca.author_service.domain.exception;

public class RestRequestException extends RuntimeException {

    private Integer statusCode;

    public RestRequestException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
