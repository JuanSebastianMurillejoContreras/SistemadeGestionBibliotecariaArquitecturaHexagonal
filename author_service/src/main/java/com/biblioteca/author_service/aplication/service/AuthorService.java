package com.biblioteca.author_service.aplication.service;

import com.biblioteca.author_service.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.author_service.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.author_service.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.author_service.aplication.port.out.BookRepositoryPort;
import com.biblioteca.author_service.domain.exception.AuthorErrorMessage;
import com.biblioteca.author_service.domain.exception.AuthorException;
import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseWithBooksDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.BookDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.BookServiceResponseDTO;
import com.biblioteca.author_service.infraestructure.mapper.IAuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    public Author getAuthorById(Long id) {

        if (id == null){
            throw new IllegalArgumentException(AuthorErrorMessage.ID_AUTOR_NOT_REGISTERED);
        }

        Author author = repositoryPort.getAuthorById(id);

        if(author == null){
            throw new IllegalArgumentException(AuthorErrorMessage.AUTOR_NOT_REGISTERED);
        }

        return author;
    }

    @Override
    public AuthorResponseWithBooksDTO getAuthorWithBooks(Long authorId) {
        Author author = repositoryPort.getAuthorById(authorId);

        // Llamada al microservicio de libros
        BookServiceResponseDTO response = bookRepositoryPort.getBooksByAuthor(authorId);

        // Devolver el autor junto con todos los metadatos del servicio de libros
        return new AuthorResponseWithBooksDTO(
                author,
                response.data(),
                response.currentPage(),
                response.totalPages(),
                response.totalElements(),
                response.pageSize()
        );
    }

}



