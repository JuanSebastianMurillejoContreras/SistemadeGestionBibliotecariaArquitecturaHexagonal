package com.biblioteca.sistemadegestionbibliotecaria.usuario.api;

import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.input.UsuarioRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.dto.out.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuarios", description = "Gestionar usuarios")
@RequestMapping("/api/v1/usuario")
public interface UsuarioApi {

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema con la información proporcionada."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario creado correctamente",
            content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))
    )
    @PostMapping
    ResponseEntity<UsuarioResponseDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para crear el usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UsuarioRequestDTO.class))
            )
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
    );
}
