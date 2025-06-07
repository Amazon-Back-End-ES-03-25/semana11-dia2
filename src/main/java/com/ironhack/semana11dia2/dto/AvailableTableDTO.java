package com.ironhack.semana11dia2.dto;

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
public class AvailableTableDTO {
    @NotNull
    private Long tableId;

    @NotNull
    @Min(1)
    private Integer capacity;

    @NotBlank
    private String availableDate;

    @NotBlank
    private String availableTime;
}
