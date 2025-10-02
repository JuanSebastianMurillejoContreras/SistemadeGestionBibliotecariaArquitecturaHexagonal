package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationException;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper.IMapperReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final SpringDataReservationRepository reservationRepository;
    private final IMapperReservation  mapperReservation;


    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(mapperReservation::toDomain);
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationEntity = mapperReservation.toEntity(reservation);
        ReservationEntity saved = reservationRepository.save(reservationEntity);
        return mapperReservation.toDomain(saved);
    }

    @Override
    public boolean existsByBook_IdAndIsActive(Long bookId, Boolean isActive) {
        return reservationRepository.existsByBook_IdAndIsActive(bookId, isActive);
    }

    @Override
    public Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable) {
        Page<ReservationEntity> entityPage = reservationRepository.findByIsActiveAndUsuario_Id(isActive, usuarioId, pageable);
        return mapperReservation.toDomainPage(entityPage, pageable);
    }

    @Override
    public Reservation cancel(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new ReservationException(ReservationErrorMessage.RESERVATION_DOES_NOT_EXIST));
        reservation.setIsActive(false);
        ReservationEntity save = reservationRepository.save(reservation);
        return mapperReservation.toDomain(save);
    }
}
