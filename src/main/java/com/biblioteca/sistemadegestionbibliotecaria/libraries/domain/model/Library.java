package com.biblioteca.library_service.domain.model;

import com.biblioteca.library_service.book.domain.model.Book;

import java.util.List;

public record Library(
         Long id,
         String name,
         String address,
         List<Book> books
) {}
