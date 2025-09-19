package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input;

public record ReservationRequestDTO (
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {}

