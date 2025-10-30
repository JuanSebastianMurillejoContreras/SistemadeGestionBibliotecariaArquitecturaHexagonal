package com.biblioteca.library_service.infraestructure.controller.dto.out;

import java.util.List;

public record BookServiceResponseDTO(
        List<BookResponseDTO> data,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}
