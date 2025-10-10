package com.biblioteca.reservation_service.domain.exception;


import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException  {

    public ReservationException(String message) {
        super(message);
    }
}