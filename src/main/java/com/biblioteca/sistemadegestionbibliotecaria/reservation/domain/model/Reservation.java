package com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model;

import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;

import java.time.LocalDateTime;

public record Reservation(
        Long id,
        Long usuarioId,
        Long bookId,
        LocalDateTime dateReservation,
        Boolean isActive
) {

    public Reservation(Long id, Long usuarioId, Long bookId, Boolean isActive) {
        this(id, usuarioId, bookId, LocalDateTime.now(), isActive);
    }
}
