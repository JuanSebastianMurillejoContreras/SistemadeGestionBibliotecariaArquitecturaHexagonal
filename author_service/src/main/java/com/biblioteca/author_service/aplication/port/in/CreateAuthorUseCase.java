package com.biblioteca.author_service.aplication.port.in;

import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorCreateCommand;

public interface CreateAuthorUseCase {

    Author createAuthor(AuthorCreateCommand author);

}
