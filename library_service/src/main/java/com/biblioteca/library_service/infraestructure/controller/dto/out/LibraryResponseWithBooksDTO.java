package com.biblioteca.library_service.infraestructure.controller.dto.out;

import com.biblioteca.library_service.domain.model.Library;

import java.util.List;

public record LibraryResponseWithBooksDTO(
        Library library,
        List<BookDTO> books,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {
}
