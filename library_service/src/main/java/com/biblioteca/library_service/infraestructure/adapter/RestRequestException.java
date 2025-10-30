package com.biblioteca.library_service.infraestructure.adapter;

public class RestRequestException extends RuntimeException {

    private Integer statusCode;

    public RestRequestException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
