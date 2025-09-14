package com.biblioteca.sistemadegestionbibliotecaria.book.service;

import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService {

    BookDTO addBook(BookCreateDTO bookCreateDTO);
    Page<BookDTO> getBookByLibraryOrAuthorOrTitle(Long libraryId, Long authorId, String title, Pageable pageable);

}