package com.biblioteca.library_service.book.aplication.port.in;

import com.biblioteca.library_service.book.domain.model.Book;
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
