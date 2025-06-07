package com.ironhack.semana11dia2.model;

import com.ironhack.semana11dia2.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@jakarta.persistence.Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String time;

    private Integer peopleCount;

    @Enumerated(value = EnumType.STRING) // para que me guarde en BBDD la palabra y no el número
    private BookingStatus status;

    @ManyToOne
    private Table table;

    @ManyToOne
    private Customer customer;
}
