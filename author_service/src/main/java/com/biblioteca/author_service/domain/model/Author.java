package com.biblioteca.author_service.domain.model;

public record Author(
        Long id,
        String name

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
