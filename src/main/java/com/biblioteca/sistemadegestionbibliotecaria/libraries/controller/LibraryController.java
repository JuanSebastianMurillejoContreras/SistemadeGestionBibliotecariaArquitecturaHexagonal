package com.biblioteca.sistemadegestionbibliotecaria.libraries.controller;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.api.LibraryApi;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.out.LibraryResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.mapper.ILibraryMapper;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.service.ILibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor
public class LibraryController implements LibraryApi {

    private final ILibraryService libraryService;
    private final ILibraryMapper libraryMapper;

    @PostMapping
        public ResponseEntity<LibraryResponseDTO> createLibrary(@RequestBody @Valid LibraryRequestDTO libraryRequestDTO) {

        LibraryCreateDTO libraryCreateDTO = libraryMapper.LibraryRequestDTOToLibraryCreateDTO(libraryRequestDTO);
        LibraryDTO addLibrary = libraryService.createLibrary(libraryCreateDTO);
        LibraryResponseDTO libraryResponseDTO = libraryMapper.LibraryDTOToLibraryResponseDTO(addLibrary);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryResponseDTO);
    }

}
