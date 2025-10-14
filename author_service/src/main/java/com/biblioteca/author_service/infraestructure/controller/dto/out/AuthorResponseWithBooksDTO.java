package com.biblioteca.author_service.infraestructure.controller.dto.out;

import java.util.List;

public record AuthorResponseWithBooksDTO(
        String name,
        List<BookDTO> books
) {}
