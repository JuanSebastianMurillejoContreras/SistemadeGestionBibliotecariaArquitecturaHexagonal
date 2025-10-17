package com.biblioteca.library_service.reservation.aplication.port.in;

import com.biblioteca.library_service.reservation.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetReservationUseCase {
    Reservation getReservationById(Long id);
    Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable);
}
