package com.ironhack.semana11dia2.dto;

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
public class BookingRequestDTO {

    @NotNull
    private Long tableId;

    @NotNull
    private Long customerId;

    @NotBlank
    private String date;

    @NotBlank
    private String time;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer peopleCount;
}
