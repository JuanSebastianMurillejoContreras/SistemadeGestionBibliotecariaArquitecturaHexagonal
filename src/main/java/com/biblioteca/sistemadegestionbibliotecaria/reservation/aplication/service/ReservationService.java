package com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.service;

import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.exception.LibraryException;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CancelReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CreateReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.GetReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationException;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements CreateReservationUseCase, CancelReservationUseCase, GetReservationUseCase {

    private final ReservationRepositoryPort repositoryPort;

    @Override
    public Reservation cancelReservation(Long id, Reservation reservation) {

        if (reservation.isActive() == null) {
            throw new IllegalArgumentException("El isActive no puede ser nulo");
        }

        if (Boolean.TRUE.equals(reservation.isActive())) {
            throw new ReservationException(ReservationErrorMessage.SOLO_SE_PERMITE_ACTUALIZAR_LA_RESERVA);
        }

        Reservation existing = repositoryPort.getReservationById(id).orElseThrow(() -> new ReservationException(ReservationErrorMessage.RESERVATION_DOES_NOT_EXIST));

        return repositoryPort.save(
                new Reservation(existing.id(), existing.usuarioId(), existing.bookId(), existing.dateReservation(), false)
        );
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if (reservation.bookId() == null){
            throw new IllegalArgumentException("El bookId no puede ser nulo");
        }

        if (reservation.usuarioId() == null){
            throw new IllegalArgumentException("El usuarioId no puede ser nulo");
        }

        if (reservation.isActive() == null){
            throw new IllegalArgumentException("El isActive no puede ser nulo");
        }

        boolean existReservation = repositoryPort.existsByBook_IdAndIsActive(reservation.bookId(), true);

        if(existReservation)
            throw new ReservationException(ReservationErrorMessage.RESERVATION_EXIST_AND_IS_ACTIVE);

        return repositoryPort.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {

        if (id <= 0) {
            throw new ReservationException(ReservationErrorMessage.ID_ERROR);
        }

        return repositoryPort.getReservationById(id)
                .orElseThrow(() -> new ReservationException(ReservationErrorMessage.RESERVATION_DOES_NOT_EXIST));
    }

    @Override
    public Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable) {
        return repositoryPort.findByIsActiveAndUsuario_Id(isActive, usuarioId, pageable);
    }

}
