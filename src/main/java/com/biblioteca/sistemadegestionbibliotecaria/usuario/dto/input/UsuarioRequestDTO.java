package com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO (
        @NotBlank(message = "El nombre del usuario no puede estar vacío")
        String name,
        @NotBlank(message = "El email del usuario no puede estar vacío")
        @Email(message = "El email registrado debe tener un formato válido: @mail.com")
        String email
) {}

