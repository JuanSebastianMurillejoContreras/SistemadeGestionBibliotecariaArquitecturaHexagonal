package com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model;

import java.time.LocalDateTime;

public record Reservation(
        Long id,
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {}
