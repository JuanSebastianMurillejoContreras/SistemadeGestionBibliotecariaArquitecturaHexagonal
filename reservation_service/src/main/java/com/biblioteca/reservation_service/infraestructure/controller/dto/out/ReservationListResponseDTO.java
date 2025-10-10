package com.biblioteca.reservation_service.infraestructure.controller.dto.out;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationResponseDTO;

import java.util.List;

public record ReservationListResponseDTO(
        List<ReservationResponseDTO> reservationResponseDTOS,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}
