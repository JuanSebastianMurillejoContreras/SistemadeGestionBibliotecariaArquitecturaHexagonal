package com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;

public interface CreateAuthorUseCase {

    Author createAuthor(Author author);

}
