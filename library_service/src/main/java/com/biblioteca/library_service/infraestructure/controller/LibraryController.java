package com.biblioteca.library_service.infraestructure.controller;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.aplication.port.in.CreateLibraryUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.model.Library;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.controller.api.LibraryApi;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.controller.dto.input.LibraryRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.controller.dto.out.LibraryResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.infraestructure.mapper.ILibraryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor
public class LibraryController implements LibraryApi {

    private final CreateLibraryUseCase CreateLibraryUseCase;
    private final ILibraryMapper libraryMapper;

    @PostMapping
        public ResponseEntity<LibraryResponseDTO> createLibrary(@RequestBody @Valid LibraryRequestDTO libraryRequestDTO) {

        Library domainLibrary = libraryMapper.toDomain(libraryRequestDTO);
        Library addLibrary = CreateLibraryUseCase.createLibrary(domainLibrary);
        LibraryResponseDTO libraryResponseDTO = libraryMapper.toResponseDTO(addLibrary);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryResponseDTO);
    }

}
