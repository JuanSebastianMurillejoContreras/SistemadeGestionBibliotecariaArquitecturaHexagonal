package com.biblioteca.library_service.book.aplication.service;

import com.biblioteca.library_service.book.aplication.port.in.CreateBookUseCase;
import com.biblioteca.library_service.book.aplication.port.in.GetBookUseCase;
import com.biblioteca.library_service.book.aplication.port.out.BookRepositoryPort;
import com.biblioteca.library_service.book.domain.exception.BookErrorMessage;
import com.biblioteca.library_service.book.domain.exception.BookException;
import com.biblioteca.library_service.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements CreateBookUseCase, GetBookUseCase {

    private final BookRepositoryPort repositoryPort;

    @Override
    public Book createBook(Book book) {

        List<String> errors = new ArrayList<>();

        if (book.title() == null) {
            throw new IllegalArgumentException("El título no puede ser nulo");
        }

        if (book.isbn() == null) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        }

        if (book.title().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        if (book.isbn().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        if (book.authorId() <= 0) {
            throw new IllegalArgumentException("El authorId debe ser mayor que 0");
        }

        if (book.libraryId()<= 0) {
            throw new IllegalArgumentException("El libraryId debe ser mayor que 0");
        }

        if (!book.isbn().matches("^(\\d{13}|\\d{3}-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1})$")) {
            throw new IllegalArgumentException("El ISBN no tiene un formato válido");
        }

        boolean existsByIsbn = repositoryPort.existsByIsbn(book.isbn());
        boolean existsByTitle = repositoryPort.existsByTitle(book.title());

        if (existsByIsbn)
            errors.add(BookErrorMessage.BOOK_ISBN_ALREADY_REGISTERED + ": " + book.isbn());
        if (existsByTitle)
            errors.add(BookErrorMessage.BOOK_NAME_ALREADY_REGISTERED + ": " + book.title());
        if (!errors.isEmpty()) throw new BookException(String.join("; ", errors));

        return repositoryPort.save(book);
    }


    @Override
    public Page<Book> buscarLibroPorIdAutorOTituloIgnorandoMasyusculaOMinuscula(Long libraryId, Long authorId, String title, Pageable pageable) {
        return  repositoryPort.findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(libraryId, authorId, title, pageable);
    }
}
