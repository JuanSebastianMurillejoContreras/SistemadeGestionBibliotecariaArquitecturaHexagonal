package com.biblioteca.library_service.infraestructure.controller.dto.out;

import java.util.List;

public record LibraryErrorResponse(
        List<String> message
) {}
