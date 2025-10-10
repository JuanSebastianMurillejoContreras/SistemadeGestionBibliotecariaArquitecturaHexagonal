package com.biblioteca.book_service.infraestructure.controller.dto.out;

public record BookResponseDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {
}
