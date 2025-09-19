package com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.book.aplication.port.out.BookRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;
import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.mapper.IBookMapper;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaBookRepositoryAdapter implements BookRepositoryPort {

    private final SpringDataBookRepository repository;
    private final IBookMapper mapper;

    @Override
    public Book save(Book book) {
        BookEntity entity = mapper.toEntity(book);
        return mapper.bookEntitytoDomain(repository.save(entity));
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    @Override
    public Page<Book> findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(Long libraryId, Long authorId, String title, Pageable pageable) {
        return mapper.toDomainPage(repository.findByLibraryIdOrAuthorIdOrTitleContainingIgnoreCase(libraryId,authorId,title,pageable));
    }
}
