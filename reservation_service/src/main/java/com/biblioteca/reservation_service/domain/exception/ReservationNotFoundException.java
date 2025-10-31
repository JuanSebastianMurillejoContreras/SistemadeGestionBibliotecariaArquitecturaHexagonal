package com.biblioteca.reservation_service.domain.exception;

import com.biblioteca.reservation_service.domain.exception.ReservationException;
import lombok.Getter;

@Getter
public class ReservationNotFoundException extends ReservationException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}