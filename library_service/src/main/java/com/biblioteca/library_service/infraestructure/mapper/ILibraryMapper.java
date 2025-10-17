package com.biblioteca.library_service.infraestructure.mapper;

import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.controller.dto.input.LibraryRequestDTO;
import com.biblioteca.library_service.infraestructure.controller.dto.out.LibraryResponseDTO;
import com.biblioteca.library_service.infraestructure.persistance.LibraryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILibraryMapper {

    // Request -> Domain
    Library toDomain(LibraryRequestDTO libraryRequestDTO);

    // Domain -> Response
    LibraryResponseDTO toResponseDTO(Library library);

    // Domain -> Entity
    LibraryEntity toEntity(Library library);

    // Entity -> Domain
    Library toDomain(LibraryEntity libraryEntity);

}
