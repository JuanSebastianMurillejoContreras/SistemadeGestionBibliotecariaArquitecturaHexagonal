package com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;

public interface CancelReservationUseCase {

    Reservation cancelReservation(Long id);

}
