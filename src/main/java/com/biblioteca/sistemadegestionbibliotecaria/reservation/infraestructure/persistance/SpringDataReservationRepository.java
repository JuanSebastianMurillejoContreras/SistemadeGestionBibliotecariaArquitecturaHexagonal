package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ReservationEntity save(ReservationEntity reservation);
    boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive);
    Page<ReservationEntity> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable);
}
