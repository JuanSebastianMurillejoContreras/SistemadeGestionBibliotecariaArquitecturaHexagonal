package com.biblioteca.sistemadegestionbibliotecaria.book.repo;

import com.biblioteca.sistemadegestionbibliotecaria.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookRepo extends JpaRepository<BookEntity, Long> {

    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);

        Page<BookEntity> findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(
                Long libraryId,
                Long authorId,
                String title,
                Pageable pageable
        );
    }
