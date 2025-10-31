package com.biblioteca.reservation_service.infraestructure.controller.dto.input;

public record ReservationCreateDTO(
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {
}
