package com.biblioteca.library_service.author.aplication.port.in;

import com.biblioteca.library_service.author.domain.model.Author;
import com.biblioteca.library_service.author.infraestructure.controller.dto.input.AuthorCreateCommand;

public interface CreateAuthorUseCase {

    Author createAuthor(AuthorCreateCommand author);

}
