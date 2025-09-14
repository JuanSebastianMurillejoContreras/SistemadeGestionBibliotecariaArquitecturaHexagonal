package com.biblioteca.sistemadegestionbibliotecaria.book.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BookRequestDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String title,

        @NotBlank(message = "El ISBN no puede estar vacío")
        @Pattern(regexp = "^(\\d{13}|\\d{3}-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1})$",
                message = "El ISBN debe tener 13 dígitos o estar en formato con guiones como 978-3-16-148410-0")
        String isbn,
        Long authorId,
        Long libraryId
) {}
