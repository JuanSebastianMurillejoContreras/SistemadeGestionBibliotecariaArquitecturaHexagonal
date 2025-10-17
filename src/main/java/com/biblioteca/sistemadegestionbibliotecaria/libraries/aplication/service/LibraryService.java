package com.biblioteca.library_service.aplication.service;

import com.biblioteca.library_service.aplication.port.in.CreateLibraryUseCase;
import com.biblioteca.library_service.aplication.port.out.LibraryRepositoryPort;
import com.biblioteca.library_service.domain.exception.LibraryErrorMessage;
import com.biblioteca.library_service.domain.exception.LibraryException;
import com.biblioteca.library_service.domain.model.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryService implements CreateLibraryUseCase {

    private final LibraryRepositoryPort repositoryPort;

    @Override
    public Library createLibrary(Library library) {

        if (library.name() == null) {
            throw new IllegalArgumentException("El nombre de la librería no puede ser nulo");
        }

        if(library.address() == null) {
            throw new IllegalArgumentException("La dirección de la librería no puede ser nula");
        }

        if (library.name().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la librería no puede estar vacio");
        }

        if(library.address().isEmpty()) {
            throw new IllegalArgumentException("La direción de la librería no puede estar vacia");
        }

        boolean existByName = repositoryPort.existsByName(library.name());

        if (existByName) {
           throw new LibraryException(LibraryErrorMessage.LIBRARY_ALREDY_REGISTERED);
        }

        return repositoryPort.createLibrary(library);
    }
}
