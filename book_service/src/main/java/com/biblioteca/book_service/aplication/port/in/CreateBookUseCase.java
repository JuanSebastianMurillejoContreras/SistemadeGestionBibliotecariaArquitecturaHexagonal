package com.biblioteca.book_service.aplication.port.in;

import com.biblioteca.book_service.domain.model.Book;

public interface CreateBookUseCase {

    Book createBook (Book book);

}
