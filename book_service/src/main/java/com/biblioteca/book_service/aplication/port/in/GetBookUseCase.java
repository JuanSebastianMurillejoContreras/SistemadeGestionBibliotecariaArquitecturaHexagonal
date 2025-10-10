package com.biblioteca.book_service.aplication.port.in;

import com.biblioteca.book_service.domain.model.Book;
import org.h2.mvstore.Page;

import java.awt.print.Pageable;


public interface GetBookUseCase {

    Page<Book> buscarLibroPorIdAutorOTituloIgnorandoMasyusculaOMinuscula(
            Long libraryId,
            Long authorId,
            String title,
            Pageable pageable
    );
}
