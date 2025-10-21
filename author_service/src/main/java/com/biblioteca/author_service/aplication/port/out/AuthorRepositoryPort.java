package com.biblioteca.author_service.aplication.port.out;

import com.biblioteca.author_service.domain.model.Author;

public interface AuthorRepositoryPort {

    Author save(Author author);
    Author getAuthorById(Long id);
    boolean existsByName(String name);

}
