package com.ironhack.semana11dia2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Table(Integer capacity) {
        this.capacity = capacity;
    }

    // table.getBookings.add(booking) <-- susituye esto
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setTable(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setTable(null);
    }
}
