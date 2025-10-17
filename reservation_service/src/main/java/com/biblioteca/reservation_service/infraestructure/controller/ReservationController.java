package com.biblioteca.reservation_service.infraestructure.controller;

import com.biblioteca.library_service.reservation.aplication.port.in.CancelReservationUseCase;
import com.biblioteca.library_service.reservation.aplication.port.in.CreateReservationUseCase;
import com.biblioteca.library_service.reservation.aplication.port.in.GetReservationUseCase;
import com.biblioteca.library_service.reservation.domain.model.Reservation;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.input.ReservationCreateDTO;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.input.ReservationRequestDTO;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.out.ReservationListResponseDTO;
import com.biblioteca.library_service.reservation.infraestructure.controller.dto.out.ReservationResponseDTO;
import com.biblioteca.library_service.reservation.infraestructure.mapper.IMapperReservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
            @PathVariable Long id,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        ReservationDTO reservationDTO = mapperReservation.
                reservationToReservationDTO(getReservationUseCase.getReservationById(id));
        Reservation reservations = mapperReservation.reservationDTOToReservation(reservationDTO);
        ReservationDTO reservationDTOToReservation = mapperReservation.toDTO(reservations);

        return ResponseEntity.ok(mapperReservation.reservationDTOToReservationResponseDTO(reservationDTOToReservation));

    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {

        // 1. Mapear entrada
        ReservationCreateDTO reservationCreateDTO =
                mapperReservation.reservationRequestDTOToReservationCreateDTO(reservationRequestDTO);

        // 2. Mapper al modelo
        Reservation reservation = mapperReservation.reservationCreateDTOToReservation(reservationCreateDTO);

        // 2. Llamar al caso de uso
        Reservation createdReservation = createReservationUseCase.createReservation(reservation);

        // 3. Mapear salida
        ReservationResponseDTO responseDTO =
                mapperReservation.reservationToReservationResponseDTO(createdReservation);

        return ResponseEntity.status(201).body(responseDTO);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationUpdateDTO reservationUpdateDTO) {

        ReservationDTO reservationDTO = mapperReservation.ReservationUpdateDTOToReservation(reservationUpdateDTO);

        Reservation reservation = mapperReservation.reservationDTOToReservation(reservationDTO);

        Reservation canceledReservation = cancelReservationUseCase.cancelReservation(id);

        // 2. Mapear salida
        ReservationResponseDTO responseDTO =
                mapperReservation.reservationToReservationResponseDTO(canceledReservation);

        return ResponseEntity.ok(responseDTO);
    }
}