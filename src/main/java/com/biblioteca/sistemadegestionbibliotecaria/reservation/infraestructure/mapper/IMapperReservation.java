package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance.BookEntity;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationListResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance.ReservationEntity;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.infraestructura.persistance.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IMapperReservation {

    @Named("mapUsuarioIdToEntity")
    default UsuarioEntity mapUsuarioIdToEntity(Long id) {
        if (id == null) return null;
        UsuarioEntity user = new UsuarioEntity();
        user.setId(id);
        return user;
    }

    @Named("mapUsuarioEntityToId")
    default Long mapUsuarioEntityToId(UsuarioEntity user) {
        return user != null ? user.getId() : null;
    }

    @Named("mapBookIdToEntity")
    default BookEntity mapBookIdToEntity(Long id) {
        if (id == null) return null;
        BookEntity book = new BookEntity();
        book.setId(id);
        return book;
    }

    @Named("mapBookEntityToId")
    default Long mapBookEntityToId(BookEntity book) {
        return book != null ? book.getId() : null;
    }

    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "mapUsuarioIdToEntity")
    @Mapping(source = "bookId", target = "book", qualifiedByName = "mapBookIdToEntity")
    ReservationEntity toEntity(Reservation reservation);

    @Mapping(source = "usuario", target = "usuarioId", qualifiedByName = "mapUsuarioEntityToId")
    @Mapping(source = "book", target = "bookId", qualifiedByName = "mapBookEntityToId")
    Reservation toDomain(ReservationEntity reservationEntity);

    @Mapping(source = "usuarioId", target = "usuarioId")
    @Mapping(source = "bookId", target = "bookId")
    ReservationDTO toDTO(Reservation reservation);


    List<ReservationResponseDTO> reservationDTOListToReservationResponseDTOList(List<ReservationDTO> reservationDTOs);

    void updateReservationEntityFromDTO(ReservationUpdateDTO dto, @MappingTarget ReservationEntity entity);

    default ReservationListResponseDTO toReservationListResponseDTO(Page<ReservationDTO> page) {
        return new ReservationListResponseDTO(
                reservationDTOListToReservationResponseDTOList(page.getContent()),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize()
        );
    }

    default Page<Reservation> toDomainPage(Page<ReservationEntity> entityPage, Pageable pageable) {
        List<Reservation> reservations = entityPage.getContent()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(reservations, pageable, entityPage.getTotalElements());
    }

}
