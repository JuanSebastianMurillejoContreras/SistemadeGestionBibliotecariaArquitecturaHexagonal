package com.biblioteca.reservation_service.infraestructure.controller.dto.input;

public record ReservationRequestDTO (
        Long usuarioId,
        Long bookId,
        Boolean isActive
) {}

