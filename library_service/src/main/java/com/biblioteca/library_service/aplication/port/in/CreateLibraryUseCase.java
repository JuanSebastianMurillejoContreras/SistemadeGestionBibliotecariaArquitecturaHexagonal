package com.biblioteca.library_service.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;

public interface CreateLibraryUseCase {

    Library createLibrary(Library library);

}
