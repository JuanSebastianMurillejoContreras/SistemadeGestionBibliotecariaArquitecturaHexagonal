package com.biblioteca.library_service.book.domain.exception;

public final class BookErrorMessage {

    public static final String BOOK_NAME_ALREADY_REGISTERED = "El nombre del libro ya se encuentra registrado en la base de datos";
    public static final String BOOK_ISBN_ALREADY_REGISTERED = "El ISBN de libro ya se encuentra registrado en la base de datos";

    private BookErrorMessage() {}

}
