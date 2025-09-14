package com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.out;

import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;

public interface AuthorRepositoryPort {

    Author save(Author author);
    Author getAuthorById(Long id);
    boolean existsByName(String name);

}
