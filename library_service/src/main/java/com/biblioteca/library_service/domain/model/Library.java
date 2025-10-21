package com.biblioteca.library_service.domain.model;

import com.biblioteca.library_service.infraestructure.controller.dto.out.PageDTO;

public record Library(
         Long id,
         String name,
         String address,
         PageDTO<Book> books
) {}
