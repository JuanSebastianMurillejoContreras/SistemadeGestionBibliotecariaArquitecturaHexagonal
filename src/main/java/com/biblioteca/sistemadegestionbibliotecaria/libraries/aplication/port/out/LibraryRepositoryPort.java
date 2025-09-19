package com.biblioteca.sistemadegestionbibliotecaria.libraries.aplication.port.out;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;

public interface LibraryRepositoryPort {

    Library createLibrary(Library library);
    boolean existsByName(String name);

}
