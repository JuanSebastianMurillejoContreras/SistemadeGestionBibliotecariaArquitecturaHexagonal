package com.biblioteca.author_service_mejorado.aplication.port.out;

import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.BookDTO;

import java.util.List;

public interface BookPort {
    List<BookDTO> getBooksByAuthor(Long authorId);
}
