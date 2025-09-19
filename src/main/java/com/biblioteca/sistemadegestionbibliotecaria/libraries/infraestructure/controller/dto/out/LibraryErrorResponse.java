package com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.controller.dto.out;

import java.util.List;

public record LibraryErrorResponse(
        List<String> message
) {}
