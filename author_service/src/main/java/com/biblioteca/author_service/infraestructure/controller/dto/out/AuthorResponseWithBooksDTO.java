package com.biblioteca.author_service.infraestructure.controller.dto.out;

import com.biblioteca.author_service.domain.model.Author;

import java.util.List;

public record AuthorResponseWithBooksDTO(
        Author author,
        List<BookDTO> books,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}