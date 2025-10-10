package com.biblioteca.author_service.aplication.port.in;


import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseDTO;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);
    AuthorResponseDTO getAuthorWithBooks(Long id);
}
