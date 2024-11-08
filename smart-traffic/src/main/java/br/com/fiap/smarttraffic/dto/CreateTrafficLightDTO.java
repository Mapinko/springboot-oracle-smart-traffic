package br.com.fiap.smarttraffic.dto;

import br.com.fiap.smarttraffic.model.TrafficLightStatus;
import lombok.Data;

@Data
public class CreateTrafficLightDTO {
    private String location;
    private TrafficLightStatus status;
    private int cycleTime;
}
