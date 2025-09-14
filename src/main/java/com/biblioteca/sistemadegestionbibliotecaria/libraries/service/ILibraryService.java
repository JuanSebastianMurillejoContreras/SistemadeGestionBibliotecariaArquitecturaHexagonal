package com.biblioteca.sistemadegestionbibliotecaria.libraries.service;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryDTO;

public interface ILibraryService {
    LibraryDTO createLibrary(LibraryCreateDTO libraryCreateDTO);
}
