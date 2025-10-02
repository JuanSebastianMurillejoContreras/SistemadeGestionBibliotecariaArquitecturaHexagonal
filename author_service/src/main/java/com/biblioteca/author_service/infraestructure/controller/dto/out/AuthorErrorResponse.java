package com.biblioteca.author_service.infraestructure.controller.dto.out;

import java.util.List;

public record AuthorErrorResponse (
        List<String> message
){}
