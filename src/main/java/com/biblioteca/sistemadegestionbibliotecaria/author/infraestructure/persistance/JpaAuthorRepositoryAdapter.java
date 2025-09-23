package com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorNotFoundException;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.mapper.IAuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAuthorRepositoryAdapter implements AuthorRepositoryPort {

    private final SpringDataAuthorRepository repository;
    private final IAuthorMapper mapper;

    @Override
    public AuthorDTO save(AuthorDTO author) {
        AuthorEntity entity = mapper.authorDTOToAuthorEntity(author);
        return mapper.authorDTOToAuthorEntity(repository.save(entity));
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

