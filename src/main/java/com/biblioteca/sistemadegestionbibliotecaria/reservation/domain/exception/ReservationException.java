package com.biblioteca.library_service.reservation.domain.exception;


import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException  {

    public ReservationException(String message) {
        super(message);
    }
}