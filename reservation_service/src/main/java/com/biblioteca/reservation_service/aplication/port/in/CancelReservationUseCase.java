package com.biblioteca.reservation_service.aplication.port.in;

import com.biblioteca.library_service.reservation.domain.model.Reservation;

public interface CancelReservationUseCase {

    Reservation cancelReservation(Long id);

}
