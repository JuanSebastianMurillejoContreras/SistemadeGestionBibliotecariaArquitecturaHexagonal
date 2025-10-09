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

    /**
     * Crea un nuevo autor validando las reglas de negocio
     */

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

    /**
     * Obtiene un autor por su ID
     */
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

    /**
     * Obtiene un autor junto con sus libros asociados (consulta externa)
     */
    @Override
    public AuthorResponseDTO getAuthorWithBooks(Long id) {
        Author author = repositoryPort.getAuthorById(id);

        if (author == null) {
            throw new AuthorException(AuthorErrorMessage.AUTOR_NOT_REGISTERED);
        }

        return new AuthorResponseDTO(
                author.id(),
                author.name(),
                List<>
        );
    }

}



