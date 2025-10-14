package com.biblioteca.book_service.infraestructure.controller.dto.out;

import java.util.List;

public record BookListResponseDTO(
        List<BookResponseDTO> bookResponseDTOList,
        PageDTO pageDTO
) {
}
