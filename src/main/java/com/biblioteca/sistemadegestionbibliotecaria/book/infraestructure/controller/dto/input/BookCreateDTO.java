package com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.controller.dto.input;

public record BookCreateDTO(
        String title,
        String isbn,
        Long authorId,
        Long libraryId
) {}
