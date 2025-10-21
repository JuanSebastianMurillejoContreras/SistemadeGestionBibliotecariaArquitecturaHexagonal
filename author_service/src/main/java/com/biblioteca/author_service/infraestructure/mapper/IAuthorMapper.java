package com.biblioteca.author_service.infraestructure.mapper;

import com.biblioteca.author_service.domain.model.Author;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.author_service.infraestructure.controller.dto.out.AuthorResponseWithBooksDTO;
import com.biblioteca.author_service.infraestructure.persistance.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthorMapper {

    // Request -> DTO
    AuthorCreateCommand authorRequestDTOToAuthorDTO(AuthorRequestDTO authorRequestDTO);
    AuthorCreateCommand authorToAuthorDTO(Author author);
    AuthorResponseDTO authorDTOToAuthorResponseDTO(AuthorCreateCommand authorCreateCommand);
    Author authorCreateCommandToAuthor(AuthorCreateCommand authorCreateCommand);

    // Domain <-> Entity
    AuthorEntity toEntity(Author author);
    Author toDomain(AuthorEntity entity);

    AuthorResponseWithBooksDTO authorToAuthorResponseWithBooksDTO(Author author);

}