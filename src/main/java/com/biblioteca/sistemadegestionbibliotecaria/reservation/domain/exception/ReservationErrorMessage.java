package com.biblioteca.library_service.reservation.domain.exception;

public final class ReservationErrorMessage {

    public static final String RESERVATION_DOES_NOT_EXIST = "La reserva no existe";
    public static final String RESERVATION_EXIST_AND_IS_ACTIVE = "El libro ha sido reservado y esta activo";
    public static final String SOLO_SE_PERMITE_ACTUALIZAR_LA_RESERVA = "Solo se permite actualizar para cancelar la reserva (isActive debe ser false)";
    public static final String ID_ERROR = "El id no puede ser menor que 0";

    private ReservationErrorMessage() {}

}
