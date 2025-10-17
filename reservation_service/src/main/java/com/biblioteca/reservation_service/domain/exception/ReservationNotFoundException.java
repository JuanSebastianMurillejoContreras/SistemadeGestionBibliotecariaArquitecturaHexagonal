package com.biblioteca.reservation_service.domain.exception;

import com.biblioteca.library_service.reservation.domain.exception.ReservationException;
import lombok.Getter;

@Getter
public class ReservationNotFoundException extends ReservationException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}