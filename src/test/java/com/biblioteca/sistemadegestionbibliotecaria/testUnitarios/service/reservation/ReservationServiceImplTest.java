package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.reservation;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.service.ReservationService;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationErrorMessage;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.exception.ReservationException;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.domain.model.Reservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper.IMapperReservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private ReservationRepositoryPort reservationRepo;
    @Spy
    private IMapperReservation reservationMapper = Mappers.getMapper(IMapperReservation.class);

    @Test
    void givenReservationRequestDTOWhenAddReservationThenReturnReservationResponseDTO() {

        LocalDateTime now = LocalDateTime.now();

        //Given
        Reservation input = new Reservation(1L, 1L, 1L, now, true);

        //When
        Reservation reservation = new Reservation(1L, 1L, 1L, now, true);

        // When
        Mockito.when(reservationRepo.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        Reservation outputEsperado = new Reservation(1L, 1L, 1L, now, true);
        Reservation resultadoEsperado = reservationService.createReservation(input);

        //Then
        assertEquals(outputEsperado, resultadoEsperado);
    }


    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullUsuarioIDThenThrowException() {
        // given
        Reservation input = new Reservation(1L, null, 1L, LocalDateTime.now(), true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.createReservation(input));
    }

    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullBookIDThenThrowException() {
        // given
        Reservation input = new Reservation(1L, 1L, null, LocalDateTime.now(), true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.createReservation(input));
    }

    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullIsActiveThenThrowException() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, LocalDateTime.now(), null);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.createReservation(input));
    }

    @Test
    void givenReservationUpdateDTOWhenCreateReservationWithNullIsActiveThenThrowException() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, LocalDateTime.now(), null);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.cancelReservation(1L, input));
    }

    @Test
    void givenReservationUpdateDTOWhenCreateReservationWithTrueIsActiveThenThrowException() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, LocalDateTime.now(), true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.cancelReservation(1L, input));
    }

    @Test
    void givenExistingReservationWhenCreateReservationThenThrowReservationException() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, true);
        Mockito.when(reservationRepo.existsByBook_IdAndIsActive(input.bookId(), true)).thenReturn(true);

        // when - then
        ReservationException ex = assertThrows(
                ReservationException.class,
                () -> reservationService.createReservation(input)
        );

        assertEquals(ReservationErrorMessage.RESERVATION_EXIST_AND_IS_ACTIVE, ex.getMessage());
        Mockito.verify(reservationRepo).existsByBook_IdAndIsActive(input.bookId(), true);
    }

    @Test
    void givenNewReservationWhenCreateReservationThenSucceed() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, true);

        Mockito.when(reservationRepo.existsByBook_IdAndIsActive(input.bookId(), true))
                .thenReturn(false);
        Mockito.when(reservationRepo.save(Mockito.any(Reservation.class))).thenReturn(input)
                .thenAnswer(invocation -> invocation.getArgument(0)); // simula persistencia

        // when
        Reservation result = reservationService.createReservation(input);

        // then
        assertNotNull(result);
        assertEquals(input.bookId(), result.bookId());
        assertEquals(input.usuarioId(), result.usuarioId());
        Mockito.verify(reservationRepo).existsByBook_IdAndIsActive(input.bookId(), true);
        Mockito.verify(reservationRepo).save(Mockito.any());
    }

    @Test
    void givenReservationInactiveWhenCreateReservationThenSucceed() {
        // given
        Reservation input = new Reservation(1L, 1L, 1L, false);
        Mockito.when(reservationRepo.existsByBook_IdAndIsActive(input.bookId(), true))
                .thenReturn(false);
        Mockito.when(reservationRepo.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Reservation result = reservationService.createReservation(input);

        // then
        assertNotNull(result);
        assertFalse(result.isActive());
        Mockito.verify(reservationRepo).existsByBook_IdAndIsActive(input.bookId(), true);
        Mockito.verify(reservationRepo).save(Mockito.any());
    }

    @Test
    void givenExistingIdWhenGetReservationByIdThenReturnReservation() {
        // given
        Long reservationId = 1L;

        Reservation expected = new Reservation(1L, 1L, 1L, true);
        Mockito.when(reservationRepo.getReservationById(reservationId))
                .thenReturn(expected);

        // when
        Reservation result = reservationService.getReservationById(reservationId);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        Mockito.verify(reservationRepo).getReservationById(reservationId);
    }

    @Test
    void givenNonExistingIdWhenGetReservationByIdThenReturnNull() {
        // given
        Long reservationId = 99L;
        Mockito.when(reservationRepo.getReservationById(reservationId))
                .thenReturn(null);

        // when
        Reservation result = reservationService.getReservationById(reservationId);

        // then
        assertNull(result);
        Mockito.verify(reservationRepo).getReservationById(reservationId);
    }

    @Test
    void givenActiveReservationsWhenFindByIsActiveAndUsuarioIdThenReturnPage() {
        // given
        Long usuarioId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Reservation reservation = new Reservation(1L, usuarioId, 1L, true);
        Page<Reservation> expectedPage = new PageImpl<>(List.of(reservation));

        Mockito.when(reservationRepo.findByIsActiveAndUsuario_Id(true, usuarioId, pageable))
                .thenReturn(expectedPage);

        // when
        Page<Reservation> result = reservationService.findByIsActiveAndUsuario_Id(true, usuarioId, pageable);

        // then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(reservation, result.getContent().get(0));
        Mockito.verify(reservationRepo).findByIsActiveAndUsuario_Id(true, usuarioId, pageable);
    }

    @Test
    void givenNoActiveReservationsWhenFindByIsActiveAndUsuarioIdThenReturnEmptyPage() {
        // given
        Long usuarioId = 2L;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Reservation> expectedPage = Page.empty(pageable);

        Mockito.when(reservationRepo.findByIsActiveAndUsuario_Id(false, usuarioId, pageable))
                .thenReturn(expectedPage);

        // when
        Page<Reservation> result = reservationService.findByIsActiveAndUsuario_Id(false, usuarioId, pageable);

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(reservationRepo).findByIsActiveAndUsuario_Id(false, usuarioId, pageable);
    }

}
