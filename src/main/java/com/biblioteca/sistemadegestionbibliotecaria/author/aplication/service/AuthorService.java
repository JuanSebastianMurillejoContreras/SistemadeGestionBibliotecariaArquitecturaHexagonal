package com.biblioteca.sistemadegestionbibliotecaria.author.aplication.service;

import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.author.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorException;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorNotFoundException;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService implements CreateAuthorUseCase, GetAuthorUseCase {

    private final AuthorRepositoryPort repositoryPort;

    @Override
    public Author createAuthor(Author author) {

        if(author.name() == null){
            throw new IllegalArgumentException("El nombre del autor no puede ser nulo");
        }

        if(author.name().isEmpty()){
            throw new IllegalArgumentException("El nombre del autor no puede ser vacío");
        }

         if (repositoryPort.existsByName(author.name())) {
            throw new AuthorException(AuthorErrorMessage.AUTOR_ALREADY_REGISTERED);
        }

        return repositoryPort.save(author);

    }

    @Override
    public Author getAuthorById(Long id) {

        if (id == null){
            throw new IllegalArgumentException("El ID del autor no puede ser nulo");
        }

        Author author = repositoryPort.getAuthorById(id);

        if(author == null){
            throw new IllegalArgumentException(AuthorErrorMessage.AUTOR_NOT_REGISTERED);
        }

        return author;
    }
}



