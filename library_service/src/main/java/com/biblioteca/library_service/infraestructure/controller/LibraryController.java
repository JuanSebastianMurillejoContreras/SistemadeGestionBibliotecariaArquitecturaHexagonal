package com.biblioteca.library_service.infraestructure.controller;

import com.biblioteca.library_service.aplication.port.in.CreateLibraryUseCase;
import com.biblioteca.library_service.aplication.port.in.GetBooksUseCase;
import com.biblioteca.library_service.aplication.port.in.GetLibraryUseCase;
import com.biblioteca.library_service.domain.model.Library;
import com.biblioteca.library_service.infraestructure.controller.api.LibraryApi;
import com.biblioteca.library_service.infraestructure.controller.dto.input.LibraryGetCommand;
import com.biblioteca.library_service.infraestructure.controller.dto.input.LibraryRequestDTO;
import com.biblioteca.library_service.infraestructure.controller.dto.out.LibraryResponseDTO;
import com.biblioteca.library_service.infraestructure.controller.dto.out.LibraryResponseWithBooksDTO;
import com.biblioteca.library_service.infraestructure.mapper.ILibraryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libraries")
@RequiredArgsConstructor
public class LibraryController implements LibraryApi {

    private final CreateLibraryUseCase CreateLibraryUseCase;
    private final GetLibraryUseCase GetLibraryUseCase;
    private final GetBooksUseCase getBooksUseCase;
    private final ILibraryMapper libraryMapper;

    @PostMapping
        public ResponseEntity<LibraryResponseDTO> createLibrary(@RequestBody @Valid LibraryRequestDTO libraryRequestDTO) {

        Library domainLibrary = libraryMapper.toDomain(libraryRequestDTO);
        Library addLibrary = CreateLibraryUseCase.createLibrary(domainLibrary);
        LibraryResponseDTO libraryResponseDTO = libraryMapper.toResponseDTO(addLibrary);

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryResponseWithBooksDTO> getLibraryById(@PathVariable Long id,
                                                             @RequestParam Boolean books,
                                                             @PageableDefault Pageable pageable) {

        LibraryGetCommand libraryGetCommand = new LibraryGetCommand(id, books, pageable);

        Library found = GetLibraryUseCase.getLibraryById(libraryGetCommand);

        LibraryResponseWithBooksDTO libraryResponseWithBooksDTO = libraryMapper.toResponseWithBooksDTO(found);

        return ResponseEntity.ok(libraryResponseWithBooksDTO);
    }

}
