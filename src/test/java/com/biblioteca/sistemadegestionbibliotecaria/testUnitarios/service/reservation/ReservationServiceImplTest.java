package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.reservation;

import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.port.out.ReservationRepositoryPort;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.aplication.service.ReservationService;
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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


}
