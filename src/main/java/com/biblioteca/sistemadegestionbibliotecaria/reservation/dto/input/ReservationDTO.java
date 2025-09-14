package com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input;

public record ReservationDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {}
