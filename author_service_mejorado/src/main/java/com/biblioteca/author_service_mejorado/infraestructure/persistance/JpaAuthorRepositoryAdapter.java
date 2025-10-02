package com.biblioteca.author_service_mejorado.infraestructure.persistance;


import com.biblioteca.author_service_mejorado.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.author_service_mejorado.domain.exception.AuthorErrorMessage;
import com.biblioteca.author_service_mejorado.domain.exception.AuthorNotFoundException;
import com.biblioteca.author_service_mejorado.domain.model.Author;
import com.biblioteca.author_service_mejorado.infraestructure.mapper.IAuthorMapper;;
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
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}

