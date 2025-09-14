package com.biblioteca.sistemadegestionbibliotecaria.book.dto.out;

public record BookResponseDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {
}
