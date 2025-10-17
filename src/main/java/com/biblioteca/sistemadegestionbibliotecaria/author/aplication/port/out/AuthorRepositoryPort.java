package com.biblioteca.library_service.author.aplication.port.out;

import com.biblioteca.library_service.author.domain.model.Author;

public interface AuthorRepositoryPort {

    Author save(Author author);
    Author getAuthorById(Long id);
    boolean existsByName(String name);

}
