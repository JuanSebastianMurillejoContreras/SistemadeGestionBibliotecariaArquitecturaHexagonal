package com.biblioteca.book_service.domain.model;

public record Book(
        Long id,
        String title,
        String isbn,
        Long authorId,
        Long libraryId
){}