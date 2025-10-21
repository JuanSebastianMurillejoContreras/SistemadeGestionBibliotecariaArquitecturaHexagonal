package com.biblioteca.library_service.aplication.port.in;

import com.biblioteca.library_service.domain.model.Library;
import org.springframework.data.domain.Pageable;

public interface GetBooksUseCase {

    Library getLibraryWithBooks(Long libraryId, Pageable pageable);

}
