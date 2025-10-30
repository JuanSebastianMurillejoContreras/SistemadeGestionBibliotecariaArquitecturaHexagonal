package com.biblioteca.library_service.infraestructure.controller.dto.out;

public record LibraryResponseWithBooksDTO(
        Long id,
        String name,
        String address,
        PageDTO<BookResponseDTO> books
) {
}
