package com.biblioteca.author_service.infraestructure.controller.dto.out;

public record AuthorResponseWithBooksDTO(
        String name,
        PageDTO<BookResponseDTO> books
) {}