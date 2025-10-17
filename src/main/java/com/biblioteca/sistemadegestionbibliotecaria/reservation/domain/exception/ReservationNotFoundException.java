package com.biblioteca.library_service.reservation.domain.exception;

import lombok.Getter;

@Getter
public class ReservationNotFoundException extends ReservationException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}