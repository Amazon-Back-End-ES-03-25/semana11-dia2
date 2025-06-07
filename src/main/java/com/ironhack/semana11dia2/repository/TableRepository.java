package com.ironhack.semana11dia2.repository;

import com.ironhack.semana11dia2.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {
    List<Table> findByCapacityGreaterThanEqual(Integer capacity);
    List<Table> findByRestaurant_Id(Long restaurantId);
}
