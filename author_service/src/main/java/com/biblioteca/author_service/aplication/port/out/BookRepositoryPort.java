package com.biblioteca.author_service.aplication.port.out;

import com.biblioteca.author_service.infraestructure.controller.dto.out.BookDTO;

import java.util.List;

public interface BookRepositoryPort {
    List<BookDTO> getBooksByAuthor(Long authorId);
}
