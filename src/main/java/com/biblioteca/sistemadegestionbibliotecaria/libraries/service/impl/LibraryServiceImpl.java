package com.biblioteca.sistemadegestionbibliotecaria.libraries.service.impl;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.constants.LibraryErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.entity.LibraryEntity;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.exception.LibraryException;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.mapper.ILibraryMapper;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.repo.ILibraryRepo;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.service.ILibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements ILibraryService {

    private final ILibraryRepo libraryRepo;
    private final ILibraryMapper libraryMapper;

    /*
    @Override
    public LibraryDTO createLibrary(LibraryCreateDTO libraryCreateDTO) {

        LibraryEntity libraryEntity = new LibraryEntity();
        libraryEntity.setName(libraryCreateDTO.name());
        libraryEntity.setAddress(libraryCreateDTO.address());

        libraryRepo.save(libraryEntity);

        LibraryDTO libraryDTO = libraryMapper.libraryEntityToLibraryDTO(libraryEntity);
        return libraryDTO;
    }
*/

    @Override
    public LibraryDTO createLibrary(LibraryCreateDTO libraryCreateDTO) {

        if (libraryCreateDTO.name() == null){
            throw new IllegalArgumentException("El nombre de la librería no puede ser nulo");
        }

        if (libraryCreateDTO.address() == null){
            throw new IllegalArgumentException("La direccion de la librería no puede ser nula");
        }

        if (libraryCreateDTO.name().isEmpty()){
            throw new IllegalArgumentException("El nombre de la librería no puede estar vacío");
        }

        if (libraryCreateDTO.address().isEmpty()){
            throw new IllegalArgumentException("La direccion de la librería no puede estar vacía");
        }

        final boolean existsLibrary = libraryRepo.existsByName(libraryCreateDTO.name());

        if (existsLibrary)
            throw new LibraryException(LibraryErrorMessage.LIBRARY_ALREDY_REGISTERED + ": " + libraryCreateDTO.name());

        LibraryEntity libraryEntity = libraryMapper.libraryCreateDTOTolibraryEntity(libraryCreateDTO);
        LibraryEntity libraryEntitySave = libraryRepo.save(libraryEntity);

        return libraryMapper.libraryEntityToLibraryDTO(libraryEntitySave);
    }

}
