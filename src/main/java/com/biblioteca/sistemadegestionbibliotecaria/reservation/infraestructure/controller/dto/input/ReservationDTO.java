package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input;

public record ReservationDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {}
