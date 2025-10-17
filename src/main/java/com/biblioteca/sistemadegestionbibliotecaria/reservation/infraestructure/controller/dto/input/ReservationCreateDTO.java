package com.biblioteca.library_service.reservation.infraestructure.controller.dto.input;

public record ReservationCreateDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {
}
