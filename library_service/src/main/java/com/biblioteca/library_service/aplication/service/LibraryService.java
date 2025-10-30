package com.biblioteca.library_service.aplication.service;

import com.biblioteca.library_service.aplication.port.in.CreateLibraryUseCase;
import com.biblioteca.library_service.aplication.port.in.GetBooksUseCase;
import com.biblioteca.library_service.aplication.port.in.GetLibraryUseCase;
import com.biblioteca.library_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.library_service.aplication.port.out.LibraryRepositoryPort;
import com.biblioteca.library_service.domain.exception.LibraryErrorMessage;
import com.biblioteca.library_service.domain.exception.LibraryException;
import com.biblioteca.library_service.domain.exception.LibraryNotFoundException;
import com.biblioteca.library_service.domain.model.Book;
import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.controller.dto.input.LibraryGetCommand;
import com.biblioteca.library_service.infraestructure.controller.dto.out.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService implements CreateLibraryUseCase, GetLibraryUseCase, GetBooksUseCase {

    private final LibraryRepositoryPort repositoryPort;
    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public Library createLibrary(Library library) {

        if (library.name() == null) {
            throw new LibraryException(LibraryErrorMessage.LIBRARY_NAME_NULL);
        }

        if(library.address() == null) {
            throw new LibraryException(LibraryErrorMessage.LIBRARY_ADDRESS_NULL);
        }

        if (library.name().isEmpty()) {
            throw new LibraryException(LibraryErrorMessage.LIBRARY_NAME_EMPTY);
        }

        if(library.address().isEmpty()) {
            throw new LibraryException(LibraryErrorMessage.LIBRARY_ADDRESS_EMPTY);
        }

        boolean existByName = repositoryPort.existsByName(library.name());

        if (existByName) {
           throw new LibraryException(LibraryErrorMessage.LIBRARY_ALREDY_REGISTERED);
        }

        return repositoryPort.createLibrary(library);
    }

    @Override
    public Library getLibraryById(LibraryGetCommand libraryGetCommand) {

        if (libraryGetCommand.libraryId() == null) {
            throw new LibraryException(LibraryErrorMessage.LIBRARY_ID_NULL);
        }
        if (libraryGetCommand.withBooks() == true) {
            return getLibraryWithBooks(libraryGetCommand.libraryId(), libraryGetCommand.pageable());
        }

        return repositoryPort.getLibraryById(libraryGetCommand.libraryId());
    }

    @Override
    public Library getLibraryWithBooks(Long libraryId, Pageable pageable) {

        if (libraryId == null) {
            throw new LibraryNotFoundException(LibraryErrorMessage.ID_LIBRARY_NOT_EXIST);
        }

        //Obtener la libraría desde el repositorio local
        Library library = repositoryPort.getLibraryById(libraryId);

        // Llamada al microservicio de libros
        PageDTO<Book> response  = bookRepositoryPort.getBooksByLibrary(libraryId, pageable);

        // Devolver el libro junto con todos los metadatos del servicio de libros
        return new Library(
                library.id(),
                library.name(),
                library.address(),
                response
        );
    }
}
