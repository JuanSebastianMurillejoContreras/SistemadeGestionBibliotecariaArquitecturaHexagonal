package com.biblioteca.author_service_mejorado.aplication.service;


import com.biblioteca.author_service_mejorado.aplication.port.in.CreateAuthorUseCase;
import com.biblioteca.author_service_mejorado.aplication.port.in.GetAuthorUseCase;
import com.biblioteca.author_service_mejorado.aplication.port.out.AuthorRepositoryPort;
import com.biblioteca.author_service_mejorado.aplication.port.out.BookPort;
import com.biblioteca.author_service_mejorado.domain.exception.AuthorErrorMessage;
import com.biblioteca.author_service_mejorado.domain.exception.AuthorException;
import com.biblioteca.author_service_mejorado.domain.model.Author;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.author_service_mejorado.infraestructure.mapper.IAuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService implements CreateAuthorUseCase, GetAuthorUseCase {

    private final AuthorRepositoryPort repositoryPort;
    private final BookPort bookPort;
    private final IAuthorMapper authorMapper;

    @Override
    public Author createAuthor(AuthorCreateCommand author) {

        if(author.name() == null){
            throw new IllegalArgumentException("El nombre del autor no puede ser nulo");
        }

        if(author.name().isEmpty()){
            throw new IllegalArgumentException("El nombre del autor no puede ser vacío");
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
            throw new IllegalArgumentException("El ID del autor no puede ser nulo");
        }

        Author author = repositoryPort.getAuthorById(id);

        if(author == null){
            throw new IllegalArgumentException(AuthorErrorMessage.AUTOR_NOT_REGISTERED);
        }

        return author;
    }

    @Override
    public AuthorResponseDTO getAuthor(Long id) {
        Author author = repositoryPort.getAuthorById(id);

        if (author == null) {
            throw new AuthorException(AuthorErrorMessage.AUTOR_NOT_REGISTERED);
        }

        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                bookPort.getBooksByAuthor(author.getId())
        );
    }



}



