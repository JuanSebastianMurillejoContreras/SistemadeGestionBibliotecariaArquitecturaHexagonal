package com.biblioteca.sistemadegestionbibliotecaria.book.dto.input;

public record BookCreateDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {}
