package com.biblioteca.library_service.reservation.infraestructure.controller.dto.input;

import java.time.LocalDateTime;

public record ReservationDTO(
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {}
