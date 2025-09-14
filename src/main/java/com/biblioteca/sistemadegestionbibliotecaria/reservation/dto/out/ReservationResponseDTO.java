package com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.out;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {
}
