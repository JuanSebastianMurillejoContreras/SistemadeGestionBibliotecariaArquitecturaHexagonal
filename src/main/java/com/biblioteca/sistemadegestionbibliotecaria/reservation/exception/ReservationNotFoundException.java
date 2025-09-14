package com.biblioteca.sistemadegestionbibliotecaria.reservation.exception;

import lombok.Getter;

@Getter
public class ReservationNotFoundException extends ReservationException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}