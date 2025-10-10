package com.biblioteca.book_service.infraestructure.controller.dto.out;

import java.util.List;

public record PageDTO<T>(
        List<T> data,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}
