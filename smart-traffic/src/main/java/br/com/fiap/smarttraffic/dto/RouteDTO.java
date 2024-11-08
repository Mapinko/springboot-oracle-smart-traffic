package br.com.fiap.smarttraffic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RouteDTO {
    private Long id;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    private double estimatedTime;
}