package com.biblioteca.author_service.domain.exception;


public final class AuthorErrorMessage {

    public static final String AUTOR_ALREADY_REGISTERED = "El autor ya está registrado en la base de datos";
    public static final String AUTOR_NOT_REGISTERED = "El autor no está registrado en la base de datos";
    public static final String ID_AUTOR_NOT_REGISTERED = "El ID del autor no está registrado en la base de datos";
    public static final String NAME_AUTOR_NOT_NULL = "El nombre del autor no puede ser nulo";
    public static final String NAME_AUTOR_NOT_EMPTY = "El nombre del autor no puede estar vacio";

    private AuthorErrorMessage() {}

}