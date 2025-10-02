package com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequestDTO(
        @NotBlank(message = "El nombre del autor no puede estar vacío")
        String name
) {}
