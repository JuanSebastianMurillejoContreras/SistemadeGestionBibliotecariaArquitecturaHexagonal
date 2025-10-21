package com.biblioteca.author_service.domain.model;

import com.biblioteca.author_service.infraestructure.controller.dto.out.PageDTO;

public record Author(
        String name,
        PageDTO<Book> books
) {}
