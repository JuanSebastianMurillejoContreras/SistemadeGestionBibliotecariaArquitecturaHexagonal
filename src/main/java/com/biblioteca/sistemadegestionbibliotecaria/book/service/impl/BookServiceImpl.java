package com.biblioteca.sistemadegestionbibliotecaria.book.service.impl;

import com.biblioteca.sistemadegestionbibliotecaria.book.constants.BookErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.entity.BookEntity;
import com.biblioteca.sistemadegestionbibliotecaria.book.exception.BookException;
import com.biblioteca.sistemadegestionbibliotecaria.book.mapper.IBookMapper;
import com.biblioteca.sistemadegestionbibliotecaria.book.repo.IBookRepo;
import com.biblioteca.sistemadegestionbibliotecaria.book.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepo bookRepo;
    private final IBookMapper bookMapper;

/*
    @Override
    public BookDTO addBook(BookCreateDTO bookCreateDTO) {

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(bookCreateDTO.authorId());

        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setId(bookCreateDTO.libraryId());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookCreateDTO.title());

        if (bookCreateDTO.isbn() == null) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        }

        if (bookCreateDTO.isbn().isBlank()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        if (!bookCreateDTO.isbn().matches("\\d{13}|\\d{3}-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d")) {
            throw new IllegalArgumentException("El ISBN no tiene un formato válido");
        }

        bookEntity.setIsbn(bookCreateDTO.isbn());
        bookEntity.setAuthor(authorEntity);
        bookEntity.setLibrary(libraryEntity);

        bookRepo.save(bookEntity);

        BookDTO bookDTO = bookMapper.bookEntityToBookDTO(bookEntity);

        return bookDTO;
    }


    Test solo pasa lo que corresponda con los valores dados
    @Override
    public BookDTO addBook(BookCreateDTO bookCreateDTO) {

        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);

        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setId(1L);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("Libro2");
        bookEntity.setIsbn("978-1000000001");
        bookEntity.setAuthor(authorEntity);
        bookEntity.setLibrary(libraryEntity);

        bookRepo.save(bookEntity);
        BookDTO bookDTO = bookMapper.bookEntityToBookDTO(bookEntity);

        return bookDTO;
    }

*/
    @Override
    public BookDTO addBook(BookCreateDTO bookCreateDTO) {

        List<String> errors = new ArrayList<>();

        if (bookCreateDTO.title() == null) {
            throw new IllegalArgumentException("El título no puede ser nulo");
        }

        if (bookCreateDTO.isbn() == null) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        }

        if (bookCreateDTO.authorId() == null) {
            throw new IllegalArgumentException("El authorId no puede ser nulo");
        }

        if (bookCreateDTO.libraryId() == null) {
            throw new IllegalArgumentException("El libraryId no puede ser nulo");
        }

        if (bookCreateDTO.title().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        if (bookCreateDTO.isbn().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        if (bookCreateDTO.authorId() <= 0) {
            throw new IllegalArgumentException("El authorId debe ser mayor que 0");
        }

        if (bookCreateDTO.libraryId() <= 0) {
            throw new IllegalArgumentException("El libraryId debe ser mayor que 0");
        }

        if (!bookCreateDTO.isbn().matches("^(\\d{13}|\\d{3}-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1})$")) {
            throw new IllegalArgumentException("El ISBN no tiene un formato válido");
        }

        boolean existsByIsbn = bookRepo.existsByIsbn(bookCreateDTO.isbn());
        boolean existsByTitle = bookRepo.existsByTitle(bookCreateDTO.title());

        if (existsByIsbn)
            errors.add(BookErrorMessage.BOOK_ISBN_ALREADY_REGISTERED + ": " + bookCreateDTO.isbn());
        if (existsByTitle)
            errors.add(BookErrorMessage.BOOK_NAME_ALREADY_REGISTERED + ": " + bookCreateDTO.title());
        if (!errors.isEmpty()) throw new BookException(String.join("; ", errors));

        BookEntity bookEntity = bookMapper.bookCreateDTOToBookEntity(bookCreateDTO);
        BookEntity saved = bookRepo.save(bookEntity);

        return bookMapper.bookEntityToBookDTO(saved);
    }



    @Override
    public Page<BookDTO> getBookByLibraryOrAuthorOrTitle(Long libraryId, Long authorId, String title, Pageable pageable) {
        Page<BookEntity> bookEntityPage = bookRepo.findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(libraryId, authorId, title, pageable);
        return bookEntityPage.map(bookMapper::bookEntityToBookDTO);
    }

}
