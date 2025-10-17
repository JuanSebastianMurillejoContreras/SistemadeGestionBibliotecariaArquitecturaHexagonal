package com.biblioteca.library_service.book.infraestructure.controller.dto.out;

import com.biblioteca.library_service.testContainers.common.dto.PageDTO;

import java.util.List;

public record BookListResponseDTO(
        List<BookResponseDTO> bookResponseDTOList,
        PageDTO pageDTO
) {
}
