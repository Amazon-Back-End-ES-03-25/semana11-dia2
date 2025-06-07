package com.ironhack.semana11dia2.dto;

import com.ironhack.semana11dia2.enums.BookingStatus;
import com.ironhack.semana11dia2.service.BookingService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

    private Long id;

    private BookingStatus status;

    private String date;

    private String time;

    private Integer peopleCount;

    private String customerUsername;

    private Long tableId;
}
