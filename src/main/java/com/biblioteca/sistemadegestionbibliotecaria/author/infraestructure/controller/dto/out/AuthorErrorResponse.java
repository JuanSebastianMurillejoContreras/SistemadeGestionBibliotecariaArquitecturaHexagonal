package com.biblioteca.library_service.author.infraestructure.controller.dto.out;

import java.util.List;

public record AuthorErrorResponse (
        List<String> message
){}
