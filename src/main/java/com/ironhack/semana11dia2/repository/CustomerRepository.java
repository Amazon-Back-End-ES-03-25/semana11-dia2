package com.ironhack.semana11dia2.repository;

import com.ironhack.semana11dia2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
