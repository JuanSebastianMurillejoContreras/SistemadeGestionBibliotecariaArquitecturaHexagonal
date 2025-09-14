package com.biblioteca.sistemadegestionbibliotecaria.book.dto.out;

import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto.PageDTO;

import java.util.List;

public record BookListResponseDTO(
        List<BookResponseDTO> bookResponseDTOList,
        PageDTO pageDTO
) {
}
