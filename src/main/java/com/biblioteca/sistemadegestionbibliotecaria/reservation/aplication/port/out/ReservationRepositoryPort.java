package com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepositoryPort {
    Reservation findById(Long id);
    Reservation getReservationById(Long id);
    Reservation save(Reservation reservation);
    boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive);
    Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable);
}
