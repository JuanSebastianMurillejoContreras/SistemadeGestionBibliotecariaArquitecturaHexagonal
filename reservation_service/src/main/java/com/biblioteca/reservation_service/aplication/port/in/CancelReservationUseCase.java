package com.biblioteca.reservation_service.aplication.port.in;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;

public interface CancelReservationUseCase {

    Reservation cancelReservation(Long id);

}
