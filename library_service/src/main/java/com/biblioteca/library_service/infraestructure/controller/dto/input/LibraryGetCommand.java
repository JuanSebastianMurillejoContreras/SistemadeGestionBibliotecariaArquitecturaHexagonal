package com.biblioteca.library_service.infraestructure.controller.dto.input;

import org.springframework.data.domain.Pageable;

public record LibraryGetCommand(
        Long libraryId,
        Boolean withBooks,
        Pageable pageable
) {
}
