package com.biblioteca.library_service.book.infraestructure.controller.dto.out;

public record BookResponseDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {
}
