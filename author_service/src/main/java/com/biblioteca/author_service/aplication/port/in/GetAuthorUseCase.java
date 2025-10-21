package com.biblioteca.author_service.aplication.port.in;

import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorGetCommand;
import org.springframework.data.domain.Pageable;

public interface GetAuthorUseCase {

    Author getAuthorById(AuthorGetCommand authorGetCommand);
    Author getAuthorWithBooks(Long authorId, Pageable pageable);
}
