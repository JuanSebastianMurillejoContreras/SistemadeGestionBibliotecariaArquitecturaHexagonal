package com.biblioteca.author_service.infraestructure.controller.dto.input;

public record AuthorGetCommand(
        Long authorId,
        Boolean withBooks
) {
}
