package br.com.fiap.smarttraffic.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String type;
    private String licensePlate;
}
