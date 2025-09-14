package com.biblioteca.sistemadegestionbibliotecaria.reservation.exception;


import lombok.Getter;

@Getter
public class ReservationException extends RuntimeException  {

    public ReservationException(String message) {
        super(message);
    }
}