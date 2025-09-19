package com.biblioteca.sistemadegestionbibliotecaria.testUnitarios.service.reservation;

import com.biblioteca.sistemadegestionbibliotecaria.book.infraestructure.persistance.BookEntity;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationCreateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.controller.dto.input.ReservationUpdateDTO;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.persistance.ReservationEntity;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.infraestructure.mapper.IMapperReservation;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.repo.IReservationRepo;
import com.biblioteca.sistemadegestionbibliotecaria.reservation.service.impl.ReservationServiceImpl;
import com.biblioteca.sistemadegestionbibliotecaria.usuario.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;
    @Mock
    private IReservationRepo reservationRepo;
    @Spy
    private IMapperReservation reservationMapper = Mappers.getMapper(IMapperReservation.class);

    @Test
    void givenReservationRequestDTOWhenAddReservationThenReturnReservationResponseDTO() {
        //Given
        ReservationCreateDTO input = new ReservationCreateDTO(1L,1L, true);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1L);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);

        //When
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(1L);
        reservationEntity.setUsuario(usuarioEntity);
        reservationEntity.setBook(bookEntity);
        reservationEntity.setIsActive(true);

        // When
        Mockito.when(reservationRepo.save(Mockito.any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationDTO outputEsperado = new ReservationDTO(1L,1L,true);
        ReservationDTO resultadoEsperado = reservationService.addReservation(input);

        //Then
        assertEquals(outputEsperado, resultadoEsperado);
    }


    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullUsuarioIDThenThrowException() {
        // given
        ReservationCreateDTO input = new ReservationCreateDTO(null, 1L, true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.addReservation(input));
    }

    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullBookIDThenThrowException() {
        // given
        ReservationCreateDTO input = new ReservationCreateDTO(1L, null, true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.addReservation(input));
    }

    @Test
    void givenReservationRequestDTOWhenCreateReservationWithNullIsActiveThenThrowException() {
        // given
        ReservationCreateDTO input = new ReservationCreateDTO(null, 1L, null);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.addReservation(input));
    }

    @Test
    void givenReservationUpdateDTOWhenCreateReservationWithNullIsActiveThenThrowException() {
        // given
        ReservationUpdateDTO input = new ReservationUpdateDTO( null);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.cancelReservation(1L, input));
    }

    @Test
    void givenReservationUpdateDTOWhenCreateReservationWithTrueIsActiveThenThrowException() {
        // given
        ReservationUpdateDTO input = new ReservationUpdateDTO( true);
        // then
        assertThrows(IllegalArgumentException.class, () -> reservationService.cancelReservation(1L, input));
    }


}
