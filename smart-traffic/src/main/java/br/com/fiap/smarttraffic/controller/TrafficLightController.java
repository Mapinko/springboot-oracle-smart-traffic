package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.TrafficLight;
import br.com.fiap.smarttraffic.model.TrafficLightStatus;
import br.com.fiap.smarttraffic.service.TrafficLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traffic-lights")
public class TrafficLightController {

    @Autowired
    private TrafficLightService trafficLightService;

    @PostMapping
    public ResponseEntity<TrafficLight> createTrafficLight(@RequestBody TrafficLight trafficLight) {
        TrafficLight createdTrafficLight = trafficLightService.createTrafficLight(trafficLight);
        return ResponseEntity.ok(createdTrafficLight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficLight> getTrafficLightById(@PathVariable Long id) {
        Optional<TrafficLight> trafficLight = trafficLightService.getTrafficLightById(id);
        return trafficLight.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TrafficLight>> getAllTrafficLights() {
        List<TrafficLight> trafficLights = trafficLightService.getAllTrafficLights();
        return ResponseEntity.ok(trafficLights);
    }

    @GetMapping("/by-location/{location}")
    public ResponseEntity<List<TrafficLight>> getTrafficLightsByLocation(@PathVariable String location) {
        List<TrafficLight> trafficLights = trafficLightService.getTrafficLightsByLocation(location);
        return ResponseEntity.ok(trafficLights);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<TrafficLight>> getTrafficLightsByStatus(@PathVariable TrafficLightStatus status) {
        List<TrafficLight> trafficLights = trafficLightService.getTrafficLightsByStatus(status);
        return ResponseEntity.ok(trafficLights);
    }

}