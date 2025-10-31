package com.biblioteca.reservation_service.infraestructure.persistance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_reserva")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateReservation;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long bookId;

    @PrePersist
    public void prePersist() {
        if (dateReservation == null) {
            dateReservation = LocalDateTime.now();
        }
    }

    @Column(nullable = false)
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dateReservation, that.dateReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateReservation);
    }

    @Override
    public String toString() {
        return "ReservationEntity{" +
                "id=" + id +
                ", dateReservation=" + dateReservation +
                '}';
    }
}
