package com.biblioteca.library_service.aplication.port.out;

import com.biblioteca.library_service.domain.model.Book;
import com.biblioteca.library_service.infraestructure.controller.dto.out.PageDTO;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryPort {
    PageDTO<Book> getBooksByLibrary(Long libraryId, Pageable pageable);
}
