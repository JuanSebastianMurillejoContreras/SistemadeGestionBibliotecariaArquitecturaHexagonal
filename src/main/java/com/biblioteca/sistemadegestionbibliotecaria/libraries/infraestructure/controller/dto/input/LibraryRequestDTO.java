package com.biblioteca.library_service.infraestructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;

public record LibraryRequestDTO(
        @NotBlank(message = "El nombre de la librería no puede estar vacío")
        String name,
        @NotBlank(message = "La dirección de la librería no puede estar vacía")
        String address
) {
}
