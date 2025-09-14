package com.biblioteca.sistemadegestionbibliotecaria.book.dto.input;

public record BookDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {}
