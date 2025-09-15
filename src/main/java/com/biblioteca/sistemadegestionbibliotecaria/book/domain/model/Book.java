package com.biblioteca.sistemadegestionbibliotecaria.book.domain.model;

public record Book(
        Long id,
        String title,
        String isbn,
        Long authorId,
        Long libraryId
){}