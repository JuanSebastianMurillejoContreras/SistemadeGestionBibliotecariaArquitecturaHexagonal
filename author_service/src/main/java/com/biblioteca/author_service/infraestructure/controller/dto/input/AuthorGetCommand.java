package com.biblioteca.author_service.infraestructure.controller.dto.input;

import org.springframework.data.domain.Pageable;

public record AuthorGetCommand(
        Long authorId,
        Boolean withBooks,
        Pageable pageable
) {}
