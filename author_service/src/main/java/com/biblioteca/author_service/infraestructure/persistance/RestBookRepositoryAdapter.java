package com.biblioteca.author_service.infraestructure.persistance;

import com.biblioteca.author_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.author_service.infraestructure.controller.dto.out.BookDTO;

import java.util.List;

public class RestBookRepositoryAdapter implements BookRepositoryPort {
    @Override
    public List<BookDTO> getBooksByAuthor(Long authorId) {
        return List.of();
    }
}
