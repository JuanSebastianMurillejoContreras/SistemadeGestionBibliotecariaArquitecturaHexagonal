package com.biblioteca.library_service.author.infraestructure.persistance;

import com.biblioteca.library_service.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.library_service.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.library_service.author.domain.exception.AuthorNotFoundException;
import com.biblioteca.library_service.author.domain.model.Author;
import com.biblioteca.library_service.author.infraestructure.mapper.IAuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAuthorRepositoryAdapter implements AuthorRepositoryPort {

    private final SpringDataAuthorRepository repository;
    private final IAuthorMapper mapper;

    @Override
    public Author save(Author author) {
        AuthorEntity entity = mapper.toEntity(author);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Author getAuthorById(Long id) {
        AuthorEntity entity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(AuthorErrorMessage.AUTOR_NOT_REGISTERED));

        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}

