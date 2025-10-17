package com.biblioteca.library_service.author.aplication.port.in;

import com.biblioteca.library_service.author.domain.model.Author;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);

}
