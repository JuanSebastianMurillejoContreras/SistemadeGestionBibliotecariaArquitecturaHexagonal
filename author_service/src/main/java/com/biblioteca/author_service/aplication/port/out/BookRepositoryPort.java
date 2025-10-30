package com.biblioteca.author_service.aplication.port.out;

import com.biblioteca.author_service.domain.model.Book;
import com.biblioteca.author_service.infraestructure.controller.dto.out.PageDTO;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryPort {

    PageDTO<Book> getBooksByAuthor(Long authorId, Pageable pageable);

}
