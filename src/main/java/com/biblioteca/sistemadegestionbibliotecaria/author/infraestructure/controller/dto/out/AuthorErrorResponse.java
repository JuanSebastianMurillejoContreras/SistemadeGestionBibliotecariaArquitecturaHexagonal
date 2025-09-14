package com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.out;

import java.util.List;

public record AuthorErrorResponse (
        List<String> message
){}
