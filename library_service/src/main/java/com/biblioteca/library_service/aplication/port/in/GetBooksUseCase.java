package com.biblioteca.library_service.aplication.port.in;

import com.biblioteca.library_service.infraestructure.controller.dto.out.LibraryResponseWithBooksDTO;

public interface GetBooksUseCase {

    LibraryResponseWithBooksDTO getLibraryWithBooks(Long id);

}
