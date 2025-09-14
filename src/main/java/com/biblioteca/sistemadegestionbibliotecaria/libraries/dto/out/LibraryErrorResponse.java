package com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.out;

import java.util.List;

public record LibraryErrorResponse(
        List<String> message
) {}
