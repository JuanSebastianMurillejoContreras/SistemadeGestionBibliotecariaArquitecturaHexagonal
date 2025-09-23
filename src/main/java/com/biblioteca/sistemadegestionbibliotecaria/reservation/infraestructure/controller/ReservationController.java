package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CancelReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CreateReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.GetReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationListResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper.IMapperReservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance.ReservationEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final GetReservationUseCase getReservationUseCase;
    private final IMapperReservation mapperReservation;

    @GetMapping
    public ResponseEntity<ReservationListResponseDTO> getReservationsActiveByUser(
            @RequestParam Long userId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Reservation> reservations = getReservationUseCase.findByIsActiveAndUsuario_Id(true, userId, pageable);

        Page<ReservationDTO> reservationDTOs = reservations.map(mapperReservation::toDTO);

        ReservationListResponseDTO reservationListResponseDTO = mapperReservation.toReservationListResponseDTO(reservationDTOs);

        return ResponseEntity.ok(reservationListResponseDTO);
    }

/*
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {

        // 1. Mapear entrada
        ReservationCreateDTO reservationCreateDTO =
                mapperReservation.reservationRequestDTOToReservationCreateDTO(reservationRequestDTO);

        // 2. Llamar al caso de uso
        ReservationDTO createdReservation = createReservationUseCase.createReservation(reservationCreateDTO);

        // 3. Mapear salida
        ReservationResponseDTO responseDTO =
                mapperReservation.reservationDTOToReservationResponseDTO(createdReservation);

        return ResponseEntity.status(201).body(responseDTO);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationUpdateDTO reservationUpdateDTO) {

        // 1. Cancelar reserva
        ReservationDTO canceledReservation = cancelReservationUseCase.cancelReservation(id, reservationUpdateDTO);

        // 2. Mapear salida
        ReservationResponseDTO responseDTO =
                mapperReservation.reservationDTOToReservationResponseDTO(canceledReservation);

        return ResponseEntity.ok(responseDTO);
    }
    */



}