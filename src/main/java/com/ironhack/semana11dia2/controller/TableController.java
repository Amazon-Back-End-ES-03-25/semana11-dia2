package com.ironhack.semana11dia2.controller;

import com.ironhack.semana11dia2.dto.AvailableTableDTO;
import com.ironhack.semana11dia2.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/availability")
    public ResponseEntity<List<AvailableTableDTO>> getAvailableTables(
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam int peopleCount) {
        return ResponseEntity.ok(tableService.findAvailableTables(date, time, peopleCount));
    }
}
