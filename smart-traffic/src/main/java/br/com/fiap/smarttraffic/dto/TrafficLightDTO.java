package br.com.fiap.smarttraffic.dto;

import br.com.fiap.smarttraffic.model.TrafficLightStatus;
import lombok.Data;

@Data
public class TrafficLightDTO {
    private Long id;
    private String location;
    private TrafficLightStatus status;
    private int cycleTime;
}

