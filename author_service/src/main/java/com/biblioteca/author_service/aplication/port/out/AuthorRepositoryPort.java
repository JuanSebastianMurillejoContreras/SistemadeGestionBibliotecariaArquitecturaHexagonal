package com.biblioteca.author_service.aplication.port.out;


import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseDTO;

public interface AuthorRepositoryPort {

    Author save(Author author);
    Author getAuthorById(Long id);
    boolean existsByName(String name);

}
