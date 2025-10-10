package com.biblioteca.book_service.infraestructure.controller.dto.input;

public record BookCreateDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {}
