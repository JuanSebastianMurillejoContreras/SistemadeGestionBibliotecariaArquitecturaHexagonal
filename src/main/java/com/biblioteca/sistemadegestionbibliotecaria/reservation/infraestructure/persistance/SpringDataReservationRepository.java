package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataReservationRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity findById(Long id);
    Reservation save(Reservation reservation);
    boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive);
    Page<ReservationEntity> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable);
}
