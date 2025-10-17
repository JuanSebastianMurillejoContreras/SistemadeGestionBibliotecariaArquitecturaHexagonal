package com.biblioteca.library_service.book.aplication.port.out;

import com.biblioteca.library_service.book.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryPort {

    Book save (Book book);
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);

    Page<Book> findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(
            Long libraryId,
            Long authorId,
            String title,
            Pageable pageable
    );

}
