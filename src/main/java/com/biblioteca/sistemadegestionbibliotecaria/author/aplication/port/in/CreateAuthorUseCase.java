package com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorCreateCommand;

public interface CreateAuthorUseCase {

    Author createAuthor(AuthorCreateCommand author);

}
