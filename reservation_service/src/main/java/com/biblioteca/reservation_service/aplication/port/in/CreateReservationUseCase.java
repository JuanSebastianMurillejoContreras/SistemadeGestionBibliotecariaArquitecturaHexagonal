package com.biblioteca.reservation_service.aplication.port.in;

import com.biblioteca.reservation_service.domain.model.Reservation;

public interface CreateReservationUseCase {
    Reservation createReservation(Reservation reservation);
}
