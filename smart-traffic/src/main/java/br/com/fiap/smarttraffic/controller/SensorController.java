package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.SensorDTO;
import br.com.fiap.smarttraffic.model.Sensor;
import br.com.fiap.smarttraffic.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    // CREATE SENSOR
    @PostMapping("/sensors")
    public ResponseEntity<SensorDTO> createSensor(@RequestBody SensorDTO sensorDTO) {
        Sensor sensor = mapToSensor(sensorDTO);
        Sensor createdSensor = sensorService.createSensor(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToSensorDTO(createdSensor));
    }

    // GET SENSOR BY ID
    @GetMapping("/sensors/{id}")
    public ResponseEntity<SensorDTO> getSensorById(@PathVariable Long id) {
        Optional<Sensor> sensorOptional = sensorService.getSensorById(id);
        if (sensorOptional.isPresent()) {
            return ResponseEntity.ok(mapToSensorDTO(sensorOptional.get()));
        } else {
            return ResponseEntity.notFound().build(); // Handle not found case
        }
    }

    // GET ALL SENSORS
    @GetMapping("/sensors")
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
        return ResponseEntity.ok(sensors.stream().map(this::mapToSensorDTO).collect(Collectors.toList()));
    }

    // Mapping methods
    private Sensor mapToSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setLocation(sensorDTO.getLocation());
        return sensor;
    }

    private SensorDTO mapToSensorDTO(Sensor sensor) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(sensor.getId());
        sensorDTO.setLocation(sensor.getLocation());
        return sensorDTO;
    }
}