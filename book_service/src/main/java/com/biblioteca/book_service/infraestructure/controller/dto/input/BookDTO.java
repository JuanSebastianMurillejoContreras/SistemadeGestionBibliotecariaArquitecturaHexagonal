package com.biblioteca.book_service.infraestructure.controller.dto.input;

public record BookDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {}
