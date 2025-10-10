package com.biblioteca.book_service.infraestructure.controller.dto.out;

import com.biblioteca.book_service.testContainers.common.dto.PageDTO;

import java.util.List;

public record BookListResponseDTO(
        List<BookResponseDTO> bookResponseDTOList,
        PageDTO pageDTO
) {
}
