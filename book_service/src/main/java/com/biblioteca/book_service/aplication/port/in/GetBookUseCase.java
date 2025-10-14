package com.biblioteca.book_service.aplication.port.in;


import com.biblioteca.book_service.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetBookUseCase {

    Page<Book> buscarLibroPorIdAutorOTituloIgnorandoMasyusculaOMinuscula(
            Long libraryId,
            Long authorId,
            String title,
            Pageable pageable
    );
}
