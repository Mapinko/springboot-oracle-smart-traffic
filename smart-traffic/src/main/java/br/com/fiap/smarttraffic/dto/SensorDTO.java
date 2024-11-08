package br.com.fiap.smarttraffic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorDTO {
    private Long id;

    @NotBlank
    private String location;
}