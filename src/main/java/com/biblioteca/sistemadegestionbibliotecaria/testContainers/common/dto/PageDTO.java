package com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto;

import java.util.List;

public record PageDTO <T>(
        List<T> data,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}
