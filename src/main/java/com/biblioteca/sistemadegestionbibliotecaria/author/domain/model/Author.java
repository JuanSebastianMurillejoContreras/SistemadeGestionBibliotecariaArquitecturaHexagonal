package com.biblioteca.sistemadegestionbibliotecaria.author.domain.model;

import com.biblioteca.sistemadegestionbibliotecaria.book.entity.BookEntity;

import java.util.List;

public record Author(
        Long id,
        String name,
        List<BookEntity> books //MAL
) {
/*
    public Author {

        if (id == null) {
            throw new IllegalArgumentException("Author id is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Author name is null");
        }
        if (books == null) {
            throw new IllegalArgumentException("Author books is null");
        }
*/
}
