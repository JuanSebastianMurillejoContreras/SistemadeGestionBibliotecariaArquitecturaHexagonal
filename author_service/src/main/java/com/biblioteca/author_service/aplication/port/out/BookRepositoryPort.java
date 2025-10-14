package com.biblioteca.author_service.aplication.port.out;

import com.biblioteca.author_service.infraestructure.controller.dto.out.BookServiceResponseDTO;

public interface BookRepositoryPort {
    BookServiceResponseDTO getBooksByAuthor(Long authorId);
}
