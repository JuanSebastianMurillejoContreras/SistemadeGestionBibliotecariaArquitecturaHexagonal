package com.biblioteca.author_service_mejorado.infraestructure.mapper;

import com.biblioteca.author_service_mejorado.domain.model.Author;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input.AuthorCreateCommand;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.author_service_mejorado.infraestructure.persistance.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthorMapper {

    // Request -> DTO
    AuthorCreateCommand authorRequestDTOToAuthorDTO(AuthorRequestDTO authorRequestDTO);
    Author authorDTOToAuthor(AuthorCreateCommand author);
    AuthorCreateCommand authorToAuthorDTO(Author author);
    AuthorResponseDTO authorDTOToAuthorResponseDTO(AuthorCreateCommand authorCreateCommand);
    Author authorCreateCommandToAuthor(AuthorCreateCommand authorCreateCommand);


    // Domain -> Response
    AuthorResponseDTO toResponseDTO(Author author);

    // Domain <-> Entity
    AuthorEntity toEntity(Author author);
    Author toDomain(AuthorEntity entity);




}
