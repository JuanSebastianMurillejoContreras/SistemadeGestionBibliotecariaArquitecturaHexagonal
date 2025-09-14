package com.biblioteca.sistemadegestionbibliotecaria.libraries.api;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.input.LibraryRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.dto.out.LibraryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Bibliotecas", description = "Gestionar bibliotecas")
@RequestMapping("/api/v1/libraries")
public interface LibraryApi {

    @Operation(
            summary = "Crear una nueva biblioteca",
            description = "Registra una nueva biblioteca en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Biblioteca creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LibraryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud",
                    content = @Content)
    })
    @PostMapping
    ResponseEntity<LibraryResponseDTO> createLibrary(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para crear la biblioteca",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LibraryRequestDTO.class))
            )
            @Valid @RequestBody LibraryRequestDTO libraryRequestDTO
    );

}
