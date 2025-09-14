package com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input;

public record ReservationCreateDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {
}
