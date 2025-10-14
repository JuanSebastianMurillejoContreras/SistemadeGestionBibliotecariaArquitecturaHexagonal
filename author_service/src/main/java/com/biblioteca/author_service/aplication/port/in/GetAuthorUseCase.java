package com.biblioteca.author_service.aplication.port.in;


import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseWithBooksDTO;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);
    AuthorResponseWithBooksDTO getAuthorWithBooks(Long id);
}
