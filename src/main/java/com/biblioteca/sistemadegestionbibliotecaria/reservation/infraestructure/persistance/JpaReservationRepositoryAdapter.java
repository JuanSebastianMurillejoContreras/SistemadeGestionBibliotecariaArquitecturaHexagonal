package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper.IMapperReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final SpringDataReservationRepository reservationRepository;
    private final IMapperReservation  mapperReservation;

    @Override
    public Reservation getReservationById(Long id) {
        ReservationEntity reservationEntity = reservationRepository.findById(id);
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = mapperReservation.toEntity(reservation);
        return mapperReservation.toDomain(reservationRepository.save(reservationEntity));
    }

    @Override
    public boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive) {
        return reservationRepository.existsByBook_IdAndIsActive(bookId, isActive);
    }

    @Override
    public Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable) {
        Page<ReservationEntity> bookEntityPage = reservationRepository.findByIsActiveAndUsuario_Id(true, usuarioId, pageable);
        return mapperReservation.toDomainPage(bookEntityPage, pageable);
    }
}
