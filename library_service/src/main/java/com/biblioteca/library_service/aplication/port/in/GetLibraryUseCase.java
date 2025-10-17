package com.biblioteca.library_service.aplication.port.in;

import com.biblioteca.library_service.domain.model.Library;

public interface GetLibraryUseCase {
    Library getLibrary(Long id);
}
