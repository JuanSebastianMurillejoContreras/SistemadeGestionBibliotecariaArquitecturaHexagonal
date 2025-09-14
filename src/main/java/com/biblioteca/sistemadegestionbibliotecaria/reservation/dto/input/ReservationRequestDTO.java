package com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input;

public record ReservationRequestDTO (
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {}

