package com.biblioteca.sistemadegestionbibliotecaria.reservation.api;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationUpdateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.out.ReservationListResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.out.ReservationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservas", description = "Gestionar reservas de libros")
@RequestMapping("/api/v1/reservations")
public interface ReservationApi {

    @Operation(
            summary = "Obtener reservas activas por usuario",
            description = "Devuelve la lista de reservas activas asociadas a un usuario específico."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida con éxito",
                    content = @Content(schema = @Schema(implementation = ReservationListResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin reservas activas",
                    content = @Content)
    })
    @GetMapping
    ResponseEntity<ReservationListResponseDTO> getReservationsActiveByUser(
            @RequestParam Long userId
    );

    @Operation(
            summary = "Crear una nueva reserva",
            description = "Registra una nueva reserva para un libro disponible."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva creada con éxito",
                    content = @Content(schema = @Schema(implementation = ReservationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o libro no disponible",
                    content = @Content)
    })
    @PostMapping
    ResponseEntity<ReservationResponseDTO> createReservation(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear la reserva",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ReservationRequestDTO.class))
            )
            @Valid @RequestBody ReservationRequestDTO reservationRequestDTO
    );

    @Operation(
            summary = "Actualizar una reserva existente",
            description = "Modifica los datos de una reserva identificada por su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva actualizada con éxito",
                    content = @Content(schema = @Schema(implementation = ReservationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada",
                    content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<ReservationResponseDTO> updateReservation(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos a actualizar en la reserva",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ReservationUpdateDTO.class))
            )
            @Valid @RequestBody ReservationUpdateDTO reservationUpdateDTO
    );
}
