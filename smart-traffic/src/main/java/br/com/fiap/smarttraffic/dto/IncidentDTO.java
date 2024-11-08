package br.com.fiap.smarttraffic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentDTO {
    private Long id;

    @NotBlank
    private String description;

    private LocalDateTime occurrenceTime;
    private String vehicleLicensePlate;
}