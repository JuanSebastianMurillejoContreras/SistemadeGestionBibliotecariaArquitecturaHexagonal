package com.biblioteca.sistemadegestionbibliotecaria.libraries.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.out.LibraryResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.entity.LibraryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILibraryMapper {

    //DTO -> ENTITY
    LibraryEntity libraryCreateDTOTolibraryEntity(LibraryCreateDTO libraryCreateDTO);

    //ENTITY -> DTO
    LibraryDTO libraryEntityToLibraryDTO(LibraryEntity libraryEntity);

    // DTO -> DTO
    LibraryCreateDTO LibraryRequestDTOToLibraryCreateDTO(LibraryRequestDTO libraryRequestDTO);
    LibraryResponseDTO LibraryDTOToLibraryResponseDTO(LibraryDTO libraryDTO);


}
