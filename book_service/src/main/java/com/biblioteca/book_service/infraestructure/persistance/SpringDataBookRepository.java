package com.biblioteca.book_service.infraestructure.persistance;

import com.biblioteca.book_service.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBookRepository extends JpaRepository<BookEntity, Long> {

    Book save(Book author);
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);

    Page<BookEntity> findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(
            Long libraryId,
            Long authorId,
            String title,
            Pageable pageable);
}
