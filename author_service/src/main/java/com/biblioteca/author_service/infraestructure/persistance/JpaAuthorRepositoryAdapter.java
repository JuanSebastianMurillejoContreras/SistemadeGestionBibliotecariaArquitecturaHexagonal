package com.biblioteca.author_service.infraestructure.persistance;


import com.biblioteca.author_service.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.author_service.domain.exception.AuthorErrorMessage;
import com.biblioteca.author_service.domain.exception.AuthorNotFoundException;
import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.author_service.infraestructure.mapper.IAuthorMapper;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public AuthorResponseDTO getAuthorWithBooks(Long id) {

        return null;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}

