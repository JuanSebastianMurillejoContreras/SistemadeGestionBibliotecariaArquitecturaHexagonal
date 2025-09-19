package com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model;

import com.biblioteca.sistemadegestionbibliotecaria.book.domain.model.Book;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;

import java.time.LocalDateTime;

public record Reservation(
        Long id,
        Long usuarioId,
        Book book,
        LocalDateTime dateReservation,
        Boolean isActive
) {

    public Reservation(Long id, Long usuarioId, Book book, Boolean isActive) {
        this(id, usuarioId, book, LocalDateTime.now(), isActive);
    }
}
