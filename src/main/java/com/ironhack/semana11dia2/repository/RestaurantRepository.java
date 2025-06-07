package com.ironhack.semana11dia2.repository;

import com.ironhack.semana11dia2.model.Customer;
import com.ironhack.semana11dia2.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByUsername(String username);
}
