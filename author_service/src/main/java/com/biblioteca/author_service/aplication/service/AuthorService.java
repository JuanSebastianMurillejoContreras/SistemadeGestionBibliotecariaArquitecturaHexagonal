package com.biblioteca.author_service.aplication.service;

import com.biblioteca.author_service.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.author_service.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.author_service.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.author_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.author_service.domain.exception.AuthorErrorMessage;
import com.biblioteca.author_service.domain.exception.AuthorException;
import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.domain.model.Book;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorGetCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.out.PageDTO;
import com.biblioteca.author_service.infraestructure.mapper.IAuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthorService implements CreateAuthorUseCase, GetAuthorUseCase {

    private final AuthorRepositoryPort repositoryPort;
    private final BookRepositoryPort bookRepositoryPort;
    private final IAuthorMapper authorMapper;

    @Override
    public Author createAuthor(AuthorCreateCommand author) {

        if(author.name() == null){
            throw new IllegalArgumentException(AuthorErrorMessage.NAME_AUTOR_NOT_NULL);
        }

        if(author.name().isEmpty()){
            throw new IllegalArgumentException(AuthorErrorMessage.NAME_AUTOR_NOT_EMPTY);
        }

         if (repositoryPort.existsByName(author.name())) {
            throw new AuthorException(AuthorErrorMessage.AUTOR_ALREADY_REGISTERED);
        }

         Author authorSave = authorMapper.authorCreateCommandToAuthor(author);

        return repositoryPort.save(authorSave);

    }

    @Override
    public Author getAuthorById(AuthorGetCommand authorGetCommand) {

        if (authorGetCommand.authorId() == null){
            throw new IllegalArgumentException(AuthorErrorMessage.ID_AUTOR_NOT_REGISTERED);
        }

        if (authorGetCommand.withBooks() == true){
            return getAuthorWithBooks(authorGetCommand.authorId(), authorGetCommand.pageable());
        }

        return repositoryPort.getAuthorById(authorGetCommand.authorId());
    }

    @Override
    public Author getAuthorWithBooks(Long authorId, Pageable pageable) {

        if (authorId == null){
            throw new IllegalArgumentException(AuthorErrorMessage.ID_AUTOR_NOT_NULL);
        }

        // Obtener el autor desde el repositorio local
        Author author = repositoryPort.getAuthorById(authorId);

        // Llamar al microservicio de libros
        PageDTO<Book> response = bookRepositoryPort.getBooksByAuthor(authorId, pageable);

        // Devolver el autor junto con todos los metadatos del servicio de libros
        return new Author(
                author.name(),
                response
        );
    }



}