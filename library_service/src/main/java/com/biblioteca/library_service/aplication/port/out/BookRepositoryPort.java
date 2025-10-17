package com.biblioteca.library_service.aplication.port.out;

import com.biblioteca.library_service.infraestructure.controller.dto.out.BookServiceResponseDTO;

public interface BookRepositoryPort {
    BookServiceResponseDTO getBooksByLibrary(Long libraryId);
}
