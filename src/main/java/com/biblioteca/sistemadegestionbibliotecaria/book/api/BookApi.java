package com.biblioteca.sistemadegestionbibliotecaria.book.api;

import com.biblioteca.sistemadegestionbibliotecaria.book.dto.input.BookRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.out.BookListResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.book.dto.out.BookResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.testContainers.common.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Libros", description = "Gestionar libros")
@RequestMapping("/api/v1/books")
public interface BookApi {

    @Operation(
            summary = "Registrar un nuevo libro",
            description = "Crea un nuevo libro en el sistema con su información básica."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Libro creado exitosamente",
                    content = @Content(schema = @Schema(implementation = BookResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud",
                    content = @Content)
    })
    @PostMapping
    ResponseEntity<BookResponseDTO> addBook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para crear el libro",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BookRequestDTO.class))
            )
            @Valid @RequestBody BookRequestDTO bookRequestDTO
    );

    @Operation(
            summary = "Buscar libros por biblioteca, autor o título",
            description = "Permite filtrar libros por biblioteca, autor y/o título. Si no se envían parámetros, devuelve todos los libros."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de libros encontrada",
                    content = @Content(schema = @Schema(implementation = BookListResponseDTO.class)))
    })
    @GetMapping
    ResponseEntity<PageDTO<BookResponseDTO>> getBookByLibraryOrAuthorOrTitle(
            @PageableDefault(page = 0, size = 5) Pageable pageable,

            @Parameter(description = "ID de la biblioteca para filtrar", example = "1")
            @RequestParam(required = false) Long libraryId,

            @Parameter(description = "ID del autor para filtrar", example = "2")
            @RequestParam(required = false) Long authorId,

            @Parameter(description = "Título del libro para filtrar", example = "Cien años de soledad")
            @RequestParam(required = false) String title
    );

}
