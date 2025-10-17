package com.biblioteca.library_service.reservation.infraestructure.controller.dto.out;

import java.util.List;

public record ReservationListResponseDTO(
        List<ReservationResponseDTO> reservationResponseDTOS,
        int currentPage,
        int totalPages,
        long totalElements,
        int pageSize
) {}
