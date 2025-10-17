package com.biblioteca.author_service.domain.model;

import java.util.List;

public record Author(
        Long id,
        String name,
        List<Book> books
) {}
