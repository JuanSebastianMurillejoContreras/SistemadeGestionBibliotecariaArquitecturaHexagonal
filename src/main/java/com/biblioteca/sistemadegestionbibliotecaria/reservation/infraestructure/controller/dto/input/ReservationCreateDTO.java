package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input;

public record ReservationCreateDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {
}
