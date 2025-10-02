package com.biblioteca.author_service.aplication.port.in;


import com.biblioteca.author_service.domain.model.Author;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);

}
