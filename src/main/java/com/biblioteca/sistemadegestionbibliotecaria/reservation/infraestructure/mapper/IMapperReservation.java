package com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper;

import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance.BookEntity;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationRequestDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationListResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.out.ReservationResponseDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance.ReservationEntity;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;
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

    // DTO -> Entity

        @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "mapUsuarioId")
        @Mapping(source = "bookId", target = "book", qualifiedByName = "mapBookId")
        ReservationEntity ReservationCreateDTOToReservationEntity(ReservationCreateDTO reservationCreateDTO);

        // tus otros mappings...

        @Named("mapBookId")
        default BookEntity mapBookId(Long id) {
            if (id == null) return null;
            BookEntity book = new BookEntity();
            book.setId(id);
            return book;
        }

        @Named("mapUsuarioId")
        default UsuarioEntity mapUsuarioId(Long id) {
            if (id == null) return null;
            UsuarioEntity user = new UsuarioEntity();
            user.setId(id);
            return user;
        }

    // Entity -> DTO
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "book.id", target = "bookId")
    ReservationDTO reservationEntityToReservationDTO(ReservationEntity reservationEntity);

    // DTO -> DTO
    List<ReservationResponseDTO> reservationDTOListToReservationResponseDTOList(List<ReservationDTO> reservationDTOs);
    ReservationCreateDTO reservationRequestDTOToReservationCreateDTO(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO reservationDTOToReservationResponseDTO(ReservationDTO reservationUpdateDTO);

    // Métodos especiales
    void updateReservationEntityFromDTO(ReservationUpdateDTO dto, @MappingTarget ReservationEntity entity);

    // Mapea un Page<ReservationDTO> a ReservationListResponseDTO
    default ReservationListResponseDTO toBookListResponseDTO(Page<ReservationDTO> page) {
        return new ReservationListResponseDTO(
                reservationDTOListToReservationResponseDTOList(page.getContent()),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize()
        );
    }

    // Convierte un Page<ReservationEntity> a Page<Reservation>
    default Page<Reservation> toDomainPage(Page<ReservationEntity> entityPage, Pageable pageable) {
        List<Reservation> reservations = entityPage.getContent()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());

        return new PageImpl<>(reservations, pageable, entityPage.getTotalElements());
    }

    // Domain -> Entity
    @Mapping(source = "usuarioId", target = "usuario", qualifiedByName = "mapUsuarioId")
    ReservationEntity toEntity(Reservation reservation);

    // Domain -> Entity
    @Mapping(source = "usuario", target = "usuarioId", qualifiedByName = "mapUsuarioId")
    Reservation toDomain( ReservationEntity reservationEntity);




}
