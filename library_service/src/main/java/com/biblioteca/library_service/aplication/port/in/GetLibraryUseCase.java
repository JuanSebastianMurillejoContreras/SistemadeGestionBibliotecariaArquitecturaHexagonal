package com.biblioteca.library_service.aplication.port.in;

import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.controller.dto.input.LibraryGetCommand;

public interface GetLibraryUseCase {
    Library getLibraryById(LibraryGetCommand libraryGetCommand);
}
