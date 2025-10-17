package com.biblioteca.library_service.book.aplication.port.in;

import com.biblioteca.library_service.book.domain.model.Book;

public interface CreateBookUseCase {

    Book createBook (Book book);

}
