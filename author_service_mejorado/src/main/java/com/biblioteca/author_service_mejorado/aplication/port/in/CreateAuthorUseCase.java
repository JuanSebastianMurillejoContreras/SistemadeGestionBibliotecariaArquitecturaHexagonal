package com.biblioteca.author_service_mejorado.aplication.port.in;


import com.biblioteca.author_service_mejorado.domain.model.Author;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input.AuthorCreateCommand;

public interface CreateAuthorUseCase {

    Author createAuthor(AuthorCreateCommand author);

}
