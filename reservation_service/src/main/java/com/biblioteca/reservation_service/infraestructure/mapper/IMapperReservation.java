package com.biblioteca.reservation_service.infraestructure.mapper;

import com.biblioteca.reservation_service.domain.model.Reservation;
import com.biblioteca.reservation_service.infraestructure.controller.dto.input.ReservationCreateDTO;
import com.biblioteca.reservation_service.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.reservation_service.infraestructure.controller.dto.input.ReservationRequestDTO;
import com.biblioteca.reservation_service.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.reservation_service.infraestructure.controller.dto.out.ReservationListResponseDTO;
import com.biblioteca.reservation_service.infraestructure.controller.dto.out.ReservationResponseDTO;
import com.biblioteca.reservation_service.infraestructure.persistance.ReservationEntity;

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

    ReservationEntity toEntity(Reservation reservation);

    Reservation toDomain(ReservationEntity reservationEntity);

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

    ReservationCreateDTO reservationRequestDTOToReservationCreateDTO(ReservationRequestDTO reservationRequestDTO);
    Reservation reservationCreateDTOToReservation(ReservationCreateDTO reservationCreateDTO);
    ReservationResponseDTO reservationToReservationResponseDTO(Reservation reservation);
    ReservationDTO ReservationUpdateDTOToReservation(ReservationUpdateDTO reservationUpdateDTO);
    Reservation reservationDTOToReservation(ReservationDTO reservationDTO);
    ReservationDTO reservationToReservationDTO(Reservation reservation);
    ReservationResponseDTO reservationDTOToReservationResponseDTO(ReservationDTO reservationDTO);
}
