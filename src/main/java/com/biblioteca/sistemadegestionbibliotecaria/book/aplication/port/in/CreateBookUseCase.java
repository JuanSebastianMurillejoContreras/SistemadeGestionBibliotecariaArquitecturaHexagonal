package com.biblioteca.sistemadegestionbibliotecaria.book.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;

public interface CreateBookUseCase {

    Book createBook (Book book);

}
