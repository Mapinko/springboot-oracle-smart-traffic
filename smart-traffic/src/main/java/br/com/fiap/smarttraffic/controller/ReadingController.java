package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.Reading;
import br.com.fiap.smarttraffic.model.Sensor;
import br.com.fiap.smarttraffic.model.Vehicle;
import br.com.fiap.smarttraffic.service.ReadingService;
import br.com.fiap.smarttraffic.service.SensorService;
import br.com.fiap.smarttraffic.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Reading> createReading(@RequestBody Reading reading) {
        Reading createdReading = readingService.createReading(reading);
        return ResponseEntity.ok(createdReading);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reading> getReadingById(@PathVariable Long id) {
        Optional<Reading> reading = readingService.getReadingById(id);
        return reading.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Reading>> getAllReadings() {
        List<Reading> readings = readingService.getAllReadings();
        return ResponseEntity.ok(readings);
    }

    @GetMapping("/by-sensor/{sensorId}")
    public ResponseEntity<List<Reading>> getReadingsBySensorId(@PathVariable Long sensorId) {
        Optional<Sensor> sensor = sensorService.getSensorById(sensorId);
        List<Reading> readings = readingService.getReadingsBySensor(sensor);
        return ResponseEntity.ok(readings);
    }

    @GetMapping("/by-vehicle/{vehicleId}")
    public ResponseEntity<List<Reading>> getReadingsByVehicleId(@PathVariable Long vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        List<Reading> readings = readingService.getReadingsByVehicle(vehicle);
        return ResponseEntity.ok(readings);
    }

}