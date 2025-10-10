package com.biblioteca.author_service.infraestructure.controller.dto.out;

import java.util.List;

public record AuthorResponseDTO(
        Long id,
        String name,
        List<BookDTO> books
) {
}
