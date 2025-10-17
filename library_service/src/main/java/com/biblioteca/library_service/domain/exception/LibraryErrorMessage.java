package com.biblioteca.library_service.domain.exception;

public final class LibraryErrorMessage {

    public static final String LIBRARY_ALREDY_REGISTERED = "La biblioteca ya esta registrada en la base de datos";
    public static final String LIBRARY_ID_NULL = "El ID de la biblioteca no puede ser nulo";
    public static final String LIBRARY_NAME_NULL = "El nombre de la librería no puede ser nulo";
    public static final String LIBRARY_ADDRESS_NULL = "La dirección de la librería no puede ser nula";
    public static final String LIBRARY_NAME_EMPTY= "El nombre de la librería no puede estar vacio";
    public static final String LIBRARY_ADDRESS_EMPTY = "La direción de la librería no puede estar vacia";
    public static final String ID_LIBRARY_NOT_EXIST = "El ID de la libreria no existe en la base de datos";
    private LibraryErrorMessage() {}
}
