package com.biblioteca.author_service_mejorado.infraestructure.controller.api;

import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.input.AuthorRequestDTO;
import com.biblioteca.author_service_mejorado.infraestructure.controller.dto.out.AuthorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/authors")
@Tag(name = "Autores", description = "Gestionar autores")
public interface AuthorApi {

    @Operation(
            summary = "Crear un nuevo autor",
            description = "Registra un nuevo autor en el sistema si no existe previamente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Autor creado exitosamente",
                    content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "El autor ya está registrado")
    })


    ResponseEntity<AuthorResponseDTO> addAuthor(
            @RequestBody(
                    description = "Datos para crear el autor",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = AuthorRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación de autor",
                                    value = "{ \"name\": \"Gabriel García Márquez\" }"
                            )
                    )
            )
            @Valid @org.springframework.web.bind.annotation.RequestBody AuthorRequestDTO authorRequestDTO
    );
}
