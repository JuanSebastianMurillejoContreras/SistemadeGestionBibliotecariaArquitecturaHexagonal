package com.biblioteca.sistemadegestionbibliotecaria.usuario.domain.exception;

public final class UsuarioErrorMessage {

    public final static String USER_NAME_ALREADY_REGISTERED = "El nombre del usuario ya se encuentra registrado en la base de datos";
    public final static String USER_EMAIL_ALREADY_REGISTERED = "El email del usuario ya se encuentra registrado en la base de datos";

    private UsuarioErrorMessage() {}

}
