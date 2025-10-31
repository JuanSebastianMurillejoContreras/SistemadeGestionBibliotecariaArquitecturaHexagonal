package com.biblioteca.reservation_service.aplication.port.in;

import com.biblioteca.reservation_service.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetReservationUseCase {
    Reservation getReservationById(Long id);
    Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable);
}
