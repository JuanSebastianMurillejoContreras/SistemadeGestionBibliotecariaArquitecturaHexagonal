package com.biblioteca.reservation_service.infraestructure.controller.dto.out;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {
}
