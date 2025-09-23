package com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.controller.dto.out.AuthorResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.author.infraestructure.persistance.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthorMapper {

    // Request -> DTO
    AuthorDTO authorRequestDTOToAuthorDTO(AuthorRequestDTO authorRequestDTO);

    //DTO -> Request
    AuthorResponseDTO authorDTOToAuthorResponseDTO( AuthorDTO authorDTO);

    // DTO -> Entity
    AuthorEntity authorDTOToAuthorEntity(AuthorDTO authorDTO);

    // Entity -> DTO
    AuthorDTO authorDTOToAuthorEntity(AuthorEntity authorEntity);

    // Request -> Domain
    Author toDomain(AuthorRequestDTO dto);

    // Domain -> Response
    AuthorResponseDTO toResponseDTO(Author author);

    // Domain <-> Entity
    AuthorEntity toEntity(Author author);
    Author toDomain(AuthorEntity entity);

}
