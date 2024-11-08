package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.CreateTrafficLightDTO;
import br.com.fiap.smarttraffic.dto.TrafficLightDTO;
import br.com.fiap.smarttraffic.model.TrafficLight;
import br.com.fiap.smarttraffic.service.TrafficLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traffic-lights")
public class TrafficLightController {

    @Autowired
    private TrafficLightService trafficLightService;

    @PostMapping
    public ResponseEntity<TrafficLightDTO> createTrafficLight(@RequestBody CreateTrafficLightDTO createTrafficLightDTO) {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.setLocation(createTrafficLightDTO.getLocation());
        trafficLight.setStatus(createTrafficLightDTO.getStatus());
        trafficLight.setCycleTime(createTrafficLightDTO.getCycleTime());

        TrafficLight createdTrafficLight = trafficLightService.createTrafficLight(trafficLight);

        TrafficLightDTO trafficLightDTO = mapToTrafficLightDTO(createdTrafficLight);

        return ResponseEntity.ok(trafficLightDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficLightDTO> getTrafficLightById(@PathVariable Long id) {
        Optional<TrafficLight> trafficLightOptional = trafficLightService.getTrafficLightById(id);
        if (trafficLightOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TrafficLight trafficLight = trafficLightOptional.get();
        TrafficLightDTO trafficLightDTO = mapToTrafficLightDTO(trafficLight);

        return ResponseEntity.ok(trafficLightDTO);
    }

    @GetMapping
    public ResponseEntity<List<TrafficLightDTO>> getAllTrafficLights() {
        List<TrafficLight> trafficLights = trafficLightService.getAllTrafficLights();
        List<TrafficLightDTO> trafficLightDTOs = mapTrafficLightsToDTOs(trafficLights);

        return ResponseEntity.ok(trafficLightDTOs);
    }


    private TrafficLightDTO mapToTrafficLightDTO(TrafficLight trafficLight) {
        TrafficLightDTO trafficLightDTO = new TrafficLightDTO();
        trafficLightDTO.setId(trafficLight.getId());
        trafficLightDTO.setLocation(trafficLight.getLocation());
        trafficLightDTO.setStatus(trafficLight.getStatus());
        trafficLightDTO.setCycleTime(trafficLight.getCycleTime());
        return trafficLightDTO;
    }

    private List<TrafficLightDTO> mapTrafficLightsToDTOs(List<TrafficLight> trafficLights) {
        List<TrafficLightDTO> trafficLightDTOs = new ArrayList<>();
        for (TrafficLight trafficLight : trafficLights) {
            trafficLightDTOs.add(mapToTrafficLightDTO(trafficLight));
        }
        return trafficLightDTOs;
    }
}