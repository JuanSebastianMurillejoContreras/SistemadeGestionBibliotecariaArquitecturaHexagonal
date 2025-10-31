package com.biblioteca.reservation_service.infraestructure.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataReservationRepository extends JpaRepository<ReservationEntity, Long> {

    ReservationEntity save(ReservationEntity reservation);
    boolean existsByBookIdAndIsActive(Long bookId, Boolean isActive);
    Page<ReservationEntity> findByIsActiveAndUsuarioId(Boolean isActive, Long usuarioId, Pageable pageable);
}
