package com.ironhack.semana11dia2.repository;

import com.ironhack.semana11dia2.model.Booking;
import com.ironhack.semana11dia2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDateAndTime(String date, String time);
    Booking findByCustomer_Username(String username);
    List<Booking> findByCustomer_Id(Long customerId);
    List<Booking> findByCustomer(Customer customer);
    List<Booking> findByTable_Id(Long tableId);
}
