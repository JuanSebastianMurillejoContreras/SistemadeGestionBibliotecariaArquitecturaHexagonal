package com.biblioteca.sistemadegestionbibliotecaria.reservation.service;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.dto.input.ReservationUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IReservationService {

    ReservationDTO addReservation(ReservationCreateDTO reservationCreateDTO);
    ReservationDTO cancelReservation(Long id, ReservationUpdateDTO reservationUpdateDTO);
    Page<ReservationDTO> findReservationActiveByUsuario(Long usuarioId, Pageable pageable);

}
