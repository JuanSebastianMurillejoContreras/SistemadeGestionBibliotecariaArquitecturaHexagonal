package com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.service;

import com.biblioteca.sistemadegestionbibliotecaria.author.domain.exception.AuthorErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.author.domain.model.Author;
import com.biblioteca.sistemadegestionbibliotecaria.libraries.domain.exception.LibraryException;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CancelReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.CreateReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.in.GetReservationUseCase;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance.ReservationEntity;
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
            throw new IllegalArgumentException("Solo se permite actualizar para cancelar la reserva (isActive debe ser false)");
        }

        // Usar el id del path, no el del body
        Reservation existing = repositoryPort.getReservationById(id);

        // Crear un nuevo record con isActive = false
        Reservation cancelled = new Reservation(
                existing.id(),
                existing.usuarioId(),
                existing.bookId(),
                existing.dateReservation(),
                false
        );

        return repositoryPort.save(cancelled);
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
            throw new LibraryException(ReservationErrorMessage.RESERVATION_EXIST_AND_IS_ACTIVE + ": ID Libro " + reservation.bookId());

        return repositoryPort.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return  repositoryPort.getReservationById(id);
    }

    @Override
    public Page<Reservation> findByIsActiveAndUsuario_Id(Boolean isActive, Long usuarioId, Pageable pageable) {
        return repositoryPort.findByIsActiveAndUsuario_Id(isActive, usuarioId, pageable);
    }

}
