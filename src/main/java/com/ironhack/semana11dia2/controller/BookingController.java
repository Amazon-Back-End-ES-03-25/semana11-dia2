package com.ironhack.semana11dia2.controller;

import com.ironhack.semana11dia2.dto.BookingRequestDTO;
import com.ironhack.semana11dia2.dto.BookingResponseDTO;
import com.ironhack.semana11dia2.enums.BookingStatus;
import com.ironhack.semana11dia2.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@Valid @RequestBody BookingRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestParam BookingStatus status) {
        bookingService.updateBookingStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(bookingService.getBookingsForCurrentUser(username));
    }
}
