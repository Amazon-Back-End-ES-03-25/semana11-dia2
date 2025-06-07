package com.ironhack.semana11dia2.service;

import com.ironhack.semana11dia2.dto.AvailableTableDTO;
import com.ironhack.semana11dia2.model.Booking;
import com.ironhack.semana11dia2.model.Table;
import com.ironhack.semana11dia2.repository.BookingRepository;
import com.ironhack.semana11dia2.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepo;
    private final BookingRepository bookingRepo;

    public List<AvailableTableDTO> findAvailableTables(String date, String time, int peopleCount) {
        // 1. Buscar mesas que puedan albergar al menos el número de personas solicitadas
        List<Table> suitableTables = tableRepo.findByCapacityGreaterThanEqual(peopleCount);
        // 2. Obtener todas las reservas existentes para esa fecha y hora
        List<Booking> bookingsAtThatTime = bookingRepo.findByDateAndTime(date, time);

        // 3. Crear una lista con los IDs de mesas que ya están reservadas en ese horario
        List<Long> bookedTableIds = new ArrayList<>();
        for (Booking b : bookingsAtThatTime) {
            Long tableId = b.getTable().getId();
            boolean alreadyAdded = false;
            for (Long id : bookedTableIds) {
                if (id.equals(tableId)) {
                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) {
                bookedTableIds.add(tableId);
            }
        }

        // 4. Filtrar las mesas disponibles (las que no están en la lista de reservadas)
        List<AvailableTableDTO> available = new ArrayList<>();
        for (Table t : suitableTables) {
            boolean isBooked = false;
            for (Long bookedId : bookedTableIds) {
                if (bookedId.equals(t.getId())) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                // Creamos un DTO
                available.add(new AvailableTableDTO(t.getId(), t.getCapacity(), date, time));
            }
        }
        // 5. Devolver la lista de mesas disponibles
        return available;
    }
}
