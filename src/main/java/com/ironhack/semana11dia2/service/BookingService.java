package com.ironhack.semana11dia2.service;

import com.ironhack.semana11dia2.dto.BookingRequestDTO;
import com.ironhack.semana11dia2.dto.BookingResponseDTO;
import com.ironhack.semana11dia2.enums.BookingStatus;
import com.ironhack.semana11dia2.exception.NotFoundException;
import com.ironhack.semana11dia2.model.Booking;
import com.ironhack.semana11dia2.model.Customer;
import com.ironhack.semana11dia2.model.Table;
import com.ironhack.semana11dia2.repository.BookingRepository;
import com.ironhack.semana11dia2.repository.CustomerRepository;
import com.ironhack.semana11dia2.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TableRepository tableRepository;
    private final CustomerRepository customerRepository;

    public BookingResponseDTO createBooking(BookingRequestDTO dto) {
        Optional<Table> optionalTable = tableRepository.findById(dto.getTableId());

        if (optionalTable.isEmpty()) {
            throw new NotFoundException("Table");
        }

        Table table = optionalTable.get();

        // lambda
        // Table table = tableRepository.findById(dto.getTableId()).orElseThrow(() -> new NotFoundException("Table"));

        Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustomerId());

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer");
        }

        Customer customer = optionalCustomer.get();

        // buscar si existen reservas para esa fecha y hora
        boolean isBooked = false;
        List<Booking> bookings = bookingRepository.findByDateAndTime(dto.getDate(), dto.getTime());
        for (Booking foundBooking : bookings) {
            // comprobar si además son para la misma mesa que yo he solicitado
            if (foundBooking.getTable().getId().equals(table.getId())) {
                isBooked = true;
                break;
            }
        }

        // si ya existen y son para la misma mesa tiro un error
        if (isBooked) {
            throw new IllegalArgumentException("Table already booked at that date and time");
        }

        Booking booking = new Booking();
        booking.setDate(dto.getDate());
        booking.setTime(dto.getTime());
        booking.setPeopleCount(dto.getPeopleCount());
        booking.setStatus(BookingStatus.CONFIRMED);

        customer.addBooking(booking);
        table.addBooking(booking);

        Booking savedBooking = bookingRepository.save(booking);

        BookingResponseDTO responseDTO = new BookingResponseDTO(savedBooking.getId(), savedBooking.getStatus(), savedBooking.getDate(),
                savedBooking.getTime(), savedBooking.getPeopleCount(), customer.getUsername(), table.getId());

        return responseDTO;
    }

    public void updateBookingStatus(Long bookingId, BookingStatus status){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new NotFoundException("Booking"));

        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new NotFoundException("Booking"));
        // bookingRepository.delete(booking); --> funciona igual
        bookingRepository.deleteById(bookingId);
    }
}
