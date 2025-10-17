package com.biblioteca.library_service.aplication.port.out;

import com.biblioteca.library_service.domain.model.Library;

public interface LibraryRepositoryPort {

    Library createLibrary(Library library);
    Library getLibraryById(Long id);
    boolean existsByName(String name);

}
