package com.biblioteca.author_service_mejorado.aplication.port.in;


import com.biblioteca.author_service_mejorado.domain.model.Author;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.AuthorResponseDTO;

public interface GetAuthorUseCase {

    Author getAuthorById(Long id);
    AuthorResponseDTO getAuthorWithBooks(Long id);
}
