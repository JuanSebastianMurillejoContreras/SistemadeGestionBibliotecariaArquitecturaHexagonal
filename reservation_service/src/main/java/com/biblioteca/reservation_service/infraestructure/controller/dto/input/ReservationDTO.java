package com.biblioteca.reservation_service.infraestructure.controller.dto.input;

import java.time.LocalDateTime;

public record ReservationDTO(
        Long id,
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {}
