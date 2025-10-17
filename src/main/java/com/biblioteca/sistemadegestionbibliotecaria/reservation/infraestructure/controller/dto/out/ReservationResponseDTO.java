package com.biblioteca.library_service.reservation.infraestructure.controller.dto.out;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {
}
